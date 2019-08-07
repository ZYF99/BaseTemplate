package com.example.basetemplate.main.architecturetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.basetemplate.R
import com.example.basetemplate.model.User

class LiveDataActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        val viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        viewModel.getUsers().observe(this, Observer<List<User>> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        })

    }
}
