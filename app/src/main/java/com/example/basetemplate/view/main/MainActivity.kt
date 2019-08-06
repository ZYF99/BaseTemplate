package com.example.basetemplate.view.main


import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.example.basetemplate.model.ObservableUser
import com.example.basetemplate.R
import com.example.basetemplate.databinding.ActivityMainBinding
import com.example.common.app.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {


    //get layout id
    override val contentLayoutId = R.layout.activity_main


    //lateinit var user: User
    var observableUser = ObservableUser()



    var count = 0

    override fun initWidget() {
        super.initWidget()

        mViewBinding = DataBindingUtil.setContentView(this,contentLayoutId)

        observableUser = ObservableUser()

        //user = User("sadas", "123456")
        mViewBinding?.userInfo = observableUser




        observableUser.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {

            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                count++
                //Toast.makeText(this@MainActivity, "object has been changed $count", Toast.LENGTH_SHORT).show()
            }
        })


        Thread(Runnable {
            observableUser.name = "AAAAAAAAAAAAAAAA"
            observableUser.password = "BBBBBBBBBBBBBBBB"
        }).start()
    }




}
