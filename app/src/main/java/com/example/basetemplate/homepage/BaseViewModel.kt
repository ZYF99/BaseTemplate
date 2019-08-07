package com.example.basetemplate.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.SingleTransformer
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(application: Application) :
    AndroidViewModel(application),
        BindLife
{

    override val compositeDisposable = CompositeDisposable()


    //fun for ste default value
    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }


    //run when this viewModel will be destroyed
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


    //resolve error in Observable
    protected fun <T> dealError(): SingleTransformer<T, T> {
        return SingleTransformer { obs ->
            obs.doOnError {
                when (it) {
                    //is ServerError -> sendError(ErrorData(ErrorType.SERVER_ERROR, it.code.toString()))
                    //is LoginException ->{}
                    //else -> sendError(ErrorData(ErrorType.UNEXPECTED))
                }
            }
        }
    }

}