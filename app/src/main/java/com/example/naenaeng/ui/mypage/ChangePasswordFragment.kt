package com.example.naenaeng.ui.mypage

import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentChangePasswordBinding

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>(R.layout.fragment_change_password) {
    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbar("비밀번호 변경")
    }

    override fun initDataBinding() {
        super.initDataBinding()
    }
}