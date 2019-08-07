package com.example.common.app


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity: AppCompatActivity() {

    //get current layout id
    abstract val contentLayoutId: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentLayoutId)
        //初始化内部界面
        initWindows()
    }

    //initAll
    private fun initWindows() {
        //get id and set it to layout

        initBefore()
        initWidget()
        initData()
    }

    //init something before initWidget
    open fun initBefore() {

    }

    //init widget
    abstract fun initWidget()

    //init data after initWidget
    abstract fun initData()


    //destroy
    override fun onDestroy() {
        super.onDestroy()
    }


}
