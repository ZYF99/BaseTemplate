package com.example.basetemplate.homepage


import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.example.basetemplate.R
import com.example.basetemplate.homepage.ui.DynamicFragment
import com.example.basetemplate.homepage.util.ErrorType
import com.example.basetemplate.homepage.util.getErrorObs
import com.example.basetemplate.homepage.util.showNoWifiDialog
import com.example.basetemplate.homepage.util.showUnexpectedDialog
import com.example.common.app.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import timber.log.Timber


class MainActivity : BaseActivity() {

    private var errorDisposable: Disposable? = null
    private var errorDialog: AlertDialog? = null

    //get layout id
    override val contentLayoutId = R.layout.activity_main


    override fun initWidget() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.maincontainer, DynamicFragment())
            setTransition(TRANSIT_FRAGMENT_FADE)
            commit()
        }
        handleError()
    }

    override fun initData() {

    }


    //解决错误
    private fun handleError(){

        errorDisposable = getErrorObs()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (!errorDialog!!.isShowing) {
                    when (it.errorType) {
                        ErrorType.NO_WIFI -> showNoWifiDialog(this) {}
                        else -> showUnexpectedDialog(this)
                    }
                }
            }.subscribe({}, { Timber.e(it) })

    }


}
