package com.example.naenaeng.ui.info

import com.example.naenaeng.MainActivity
import com.example.naenaeng.R
import com.example.naenaeng.base.BaseFragment
import com.example.naenaeng.databinding.FragmentFindIdpwBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FindIdPwFragment  : BaseFragment<FragmentFindIdpwBinding>(R.layout.fragment_find_idpw) {
    override fun initStartView() {
        super.initStartView()
        (activity as MainActivity).setToolbarTitle("ID/PW찾기")

    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.pager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.pager){tab, posiiton ->
            when(posiiton){
                0 -> tab.text = "아이디 찾기"
                1 -> tab.text = "비밀번호 찾기"
            }
        }.attach()

    }
}