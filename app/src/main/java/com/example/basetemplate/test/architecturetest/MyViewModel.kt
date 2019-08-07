package com.example.basetemplate.main.architecturetest

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.basetemplate.model.User
import com.example.basetemplate.ui.base.BaseViewModel

class MyViewModel(private val users: MutableLiveData<List<User>>, application: Application) :
    BaseViewModel(application) {

    fun getUsers(): LiveData<List<User>> {
        loadUsers()
        return users
    }

    fun loadUsers() {
        //todo asyncronous operation to fetch users
    }


}