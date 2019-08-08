package com.example.basetemplate.main.ui.homepage

import android.view.View
import android.widget.Toast
import com.example.basetemplate.R
import com.example.basetemplate.databinding.HomepageBinding
import com.example.basetemplate.main.ui.find.FindViewModel
import com.example.basetemplate.main.BindingFragment
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_homepage.*

class HomepageFragment : BindingFragment<HomepageBinding, HomepageViewModel>(
    HomepageViewModel::class.java, R.layout.fragment_homepage) {

    override fun initBefore() {

    }

    override fun initWidget(view: View) {
        RxSwipeRefreshLayout.refreshes(binding.refreshlayout)
            .doOnNext {
                Toast.makeText(context,"AAAA",Toast.LENGTH_SHORT).show()
                viewModel.refreshing.value = false
                viewModel.refreshing.postValue(false)
            }

            .bindLife()
    }

    override fun initData() {

    }


}