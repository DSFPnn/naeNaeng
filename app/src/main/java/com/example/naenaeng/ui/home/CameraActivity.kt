package com.example.naenaeng.ui.home

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.naenaeng.MyApplication
import com.example.naenaeng.databinding.ActivityCameraBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ml.modeldownloader.CustomModel
import com.google.firebase.ml.modeldownloader.CustomModelDownloadConditions
import com.google.firebase.ml.modeldownloader.DownloadType
import com.google.firebase.ml.modeldownloader.FirebaseModelDownloader
import org.tensorflow.lite.Interpreter
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.EnumSet.range


class CameraActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCameraBinding
    private val db = Firebase.firestore
    private val REQUEST_IMAGE_CAPTURE:Int = 1
    private lateinit var imageBitmap : Bitmap
    private lateinit var interpreter : Interpreter
    private val ingredRef = db.collection("public")
    private var imageClass: String = "null jpg"
    private var result : String =""
    private var plusDay : Int = 0
    private var trainNum :Int = 2
    private var imageInt = "-1"
    private val getResultImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    Log.d("cameraa", "result_ok$result")
                    imageBitmap = result.data?.extras?.get("data") as Bitmap
                    binding.preview.setImageBitmap(imageBitmap)
                    binding.btnOk.visibility = View.VISIBLE
                    binding.btnTakePhoto.text = "다시 촬영"
                }
            }
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

        checkPermission()


        binding.btnTakePhoto.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                    getResultImage.launch(takePictureIntent)
                }
            }
            else{
                Toast.makeText(this,"해당 안드로이드 버전으로는 카메라를 사용할 수 없습니다.",Toast.LENGTH_LONG).show()
            }
        }
        binding.btnOk.setOnClickListener {

            val conditions = CustomModelDownloadConditions.Builder()
                .requireWifi()  // Also possible: .requireCharging() and .requireDeviceIdle()
                .build()

            FirebaseModelDownloader.getInstance()
                .getModel("TestModel", DownloadType.LOCAL_MODEL_UPDATE_IN_BACKGROUND,
                    conditions)
                .addOnSuccessListener { model: CustomModel? ->
                    // Download complete. Depending on your app, you could enable the ML
                    // feature, or switch from the local model to the remote model, etc.

                    // The CustomModel object contains the local path of the model file,
                    // which you can use to instantiate a TensorFlow Lite interpreter.
                    val modelFile = model?.file
                    if (modelFile != null) {
                        interpreter = Interpreter(modelFile)
                    }

                    val bitmap = Bitmap.createScaledBitmap(imageBitmap, 128, 128, true)
                    val input = ByteBuffer.allocateDirect(128*128*3*4).order(ByteOrder.nativeOrder())
                    for (y in 0 until 128) {
                        for (x in 0 until 128) {
                            val px = bitmap.getPixel(x, y)

                            // Get channel values from the pixel value.
                            val r = Color.red(px)
                            val g = Color.green(px)
                            val b = Color.blue(px)

                            // Normalize channel values to [-1.0, 1.0]. This requirement depends on the model.
                            // For example, some models might require values to be normalized to the range
                            // [0.0, 1.0] instead.
                            val rf = (r - 127) / 255f
                            val gf = (g - 127) / 255f
                            val bf = (b - 127) / 255f

                            input.putFloat(rf)
                            input.putFloat(gf)
                            input.putFloat(bf)
                        }
                    }

                    val bufferSize = trainNum * java.lang.Float.SIZE / java.lang.Byte.SIZE
                    val modelOutput = ByteBuffer.allocateDirect(bufferSize).order(ByteOrder.nativeOrder())
                    interpreter.run(input, modelOutput)

                    modelOutput.rewind()
                    val probabilities = modelOutput.asFloatBuffer()
                    var temp = 0F

                    val reader = BufferedReader(
                        InputStreamReader(assets.open("test_labels.txt")))
                        for (i in 0 until trainNum) {
                            val label: String = reader.readLine()
                            val probability = probabilities.get(i)
                            println("$label: $probability")
                            if (temp < probability){
                                temp = probability
                                result = label
                            }
                    }
                    println(result)

                    //재료 확인 메시지
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("인식된 식재료 추가하기")
                        .setMessage(result+"를 추가하시겠습니까?")
                        .setPositiveButton("추가",
                            DialogInterface.OnClickListener { dialog, id ->
                                ingredRef.document("ingredient").get().addOnSuccessListener {
                                    val ingred = it.data
                                    val ingredType = ingred?.get("ingredType") as HashMap<String, ArrayList<String>>
                                    val ingredDate = ingred?.get("date") as HashMap<String, ArrayList<String>>
                                    val typeList = ingred?.get("typeName") as ArrayList<String>
                                    val dateList = ingred?.get("dateList") as ArrayList<String>
                                    for (type in typeList) {
                                        val typeArray = ingredType?.get(type)
                                        if (typeArray != null) {
                                            if (result in typeArray) {
                                                imageClass = type
                                            }
                                        }
                                    }

                                    for (n in dateList) {
                                        val dateArray = ingredDate?.get(n)
                                        if (dateArray != null) {
                                            if (result in dateArray) {
                                                 plusDay = n.toInt()
                                            }
                                        }
                                    }

                                    var now = LocalDate.now()
                                    var date = now.plusDays(plusDay.toLong())
                                    var ingredientDate = date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))


                                    imageInt = MyApplication.ingredientIamgeHash[imageClass].toString()
                                    val data = hashMapOf(
                                        "name" to result,
                                        "date" to ingredientDate,
                                        "added" to today(),
                                        "imageClass" to imageClass,
                                        "imageInt" to imageInt
                                    )
                                    //firestore에 재료추가
                                    db.collection("users").document(MyApplication.prefs.getString("email", "null"))
                                        .update("ingredients", FieldValue.arrayUnion(data))

                                    finish()
                                }
                            })
                        .setNegativeButton("취소",
                            DialogInterface.OnClickListener { dialog, id ->

                            })
                    builder.show()
                }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_IMAGE_CAPTURE) {
            var count = grantResults.count { it == PackageManager.PERMISSION_DENIED }

            if(count != 0) {
                Toast.makeText(applicationContext, "권한을 동의해주세요.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun setToolbar(){
        val toolbar = binding.toolbar2
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        // Toolbar에 표시되는 제목의 표시 유무를 설정. false로 해야 custom한 툴바의 이름이 화면에 보인다.
        ab.setDisplayShowTitleEnabled(false)

        binding.btnFinish.setOnClickListener {
            finish()
        }
    }

    private fun checkPermission() {
        var permission = mutableMapOf<String, String>()
        permission["camera"] = Manifest.permission.CAMERA
//        permission["storageRead"] = Manifest.permission.READ_EXTERNAL_STORAGE
//        permission["storageWrite"] =  Manifest.permission.WRITE_EXTERNAL_STORAGE

        var denied = permission.count { ContextCompat.checkSelfPermission(this, it.value)  == PackageManager.PERMISSION_DENIED }

        if(denied > 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission.values.toTypedArray(), REQUEST_IMAGE_CAPTURE)
        }

    }

    private fun today(): String {
        val currentTime = System.currentTimeMillis()

        return currentTime.toString()
    }


}