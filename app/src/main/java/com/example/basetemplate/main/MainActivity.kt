package com.example.basetemplate.main


import android.annotation.SuppressLint
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.example.basetemplate.R
import com.example.basetemplate.main.ui.find.FindFragment
import com.example.basetemplate.main.ui.homepage.HomepageFragment
import com.example.basetemplate.main.ui.mine.MineFragment
import com.example.basetemplate.main.util.ErrorType
import com.example.basetemplate.main.util.getErrorObs
import com.example.basetemplate.main.util.showNoWifiDialog
import com.example.basetemplate.main.util.showUnexpectedDialog
import com.example.common.app.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import timber.log.Timber


class MainActivity : BaseActivity() {

    private var errorDisposable: Disposable? = null
    private var errorDialog: AlertDialog? = null
    //get layout id
    override val contentLayoutId = R.layout.activity_main


    @SuppressLint("ResourceType")
    override fun initWidget() {


        supportFragmentManager.beginTransaction().apply {
            add(R.id.maincontainer, HomepageFragment())
            //addToBackStack(null)
            setTransition(TRANSIT_FRAGMENT_FADE)
            commit()
        }

        handleError()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.maincontainer, HomepageFragment()).commit()
                }
                R.id.navigation_find -> {
                    supportFragmentManager.beginTransaction().replace(R.id.maincontainer, FindFragment()).commit()
                }
                R.id.navigation_mine -> {
                    supportFragmentManager.beginTransaction().replace(R.id.maincontainer, MineFragment()).commit()
                }
            }
            true
        }
    }

    override fun initData() {

    }


    //实际'异常'处理者
    private fun handleError() {

        errorDisposable = getErrorObs()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (!errorDialog!!.isShowing) {
                    errorDialog = when (it.errorType) {
                        ErrorType.NO_WIFI -> showNoWifiDialog(this) {}
                        else -> showUnexpectedDialog(this)
                    }
                }
            }.subscribe({}, { Timber.e(it) })

    }

    override fun onDestroy() {
        errorDialog?.dismiss()
        errorDialog = null
        errorDisposable?.dispose()
        super.onDestroy()
    }


}
