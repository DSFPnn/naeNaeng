package com.example.naenaeng.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VM : ViewModel, B: ViewDataBinding> (@LayoutRes private  val layoutResourceId: Int) :
    Fragment() {

    protected abstract val viewModel: VM
    protected lateinit var binding: B
    //private lateinit var navController: NavController

    protected open fun initStartView() {}
    protected open fun initDataBinding() {}
    protected open fun initAfterBinding() {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //navController = findNavController()

        initStartView()
        initDataBinding()
        initAfterBinding()
    }
}