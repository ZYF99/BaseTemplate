package com.example.basetemplate.homepage.ui

import android.view.View
import com.example.basetemplate.R
import com.example.basetemplate.databinding.DynamicBinding
import com.example.common.app.BindingFragment

class DynamicFragment :BindingFragment<DynamicBinding,DynamicViewModel>(
    DynamicViewModel::class.java, R.layout.fragment_dynamic) {




    override fun initBefore() {

    }

    override fun initWidget(view: View) {
    }

    override fun initData() {
    }


}