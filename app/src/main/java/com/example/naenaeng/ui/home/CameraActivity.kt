package com.example.naenaeng.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.naenaeng.MyApplication
import com.example.naenaeng.R
import com.example.naenaeng.databinding.ActivityCameraBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

class CameraActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCameraBinding
    private val db = Firebase.firestore
    private val REQUEST_IMAGE_CAPTURE:Int = 1
    private val getResultImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    Log.d("cameraa", "result_ok$result")
                    val imageBitmap = result.data?.extras?.get("data") as Bitmap
                    binding.preview.setImageBitmap(imageBitmap)
                    binding.btnOk.visibility = View.VISIBLE
                    binding.btnTakePhoto.text = "다시 촬영"
                }
            }
        }

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
            val data =  hashMapOf(
                "name" to "망고",
                "date" to "2022년 10월 10일",
                "added" to today(),
                "imageClass" to "null jpg"
            )

            //firestore에 재료추가
            db.collection("users").document(MyApplication.prefs.getString("email","null"))
                .update("ingredients", FieldValue.arrayUnion(data))

            finish()
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
        val dataFormat = SimpleDateFormat("yyyyMMdd")

        return dataFormat.format(currentTime).toString()
    }
}