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
    private lateinit var bottomNavigation:BottomNavigationView
    //get layout id
    override val contentLayoutId = R.layout.activity_main



    @SuppressLint("ResourceType")
    override fun initWidget() {
        bottomNavigation = findViewById(R.id.bottomNavigationView)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.maincontainer, HomepageFragment())
            add(R.id.maincontainer, FindFragment())
            add(R.id.maincontainer, MineFragment())
            setTransition(TRANSIT_FRAGMENT_FADE)
            commit()
        }
        handleError()
        bottomNavigation.setOnNavigationItemSelectedListener {
            supportFragmentManager.beginTransaction().apply {

                when(it.itemId)  {
                    R.id.navigation_home -> {replace(R.layout.fragment_homepage, HomepageFragment()).commit()}
                    R.id.navigation_find -> {replace(R.layout.fragment_find, FindFragment()).commit()}
                    R.id.navigation_mine -> {replace(R.layout.fragment_mine, MineFragment()).commit()}
                }

            }

            true

        }
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
