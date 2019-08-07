package com.example.common.app


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.basetemplate.ui.base.BindLife
import io.reactivex.disposables.CompositeDisposable

abstract class BindingFragment<Bind : ViewDataBinding, VM : AndroidViewModel>
constructor(
    private val clazz: Class<VM>,
    private val bindingCreator: (LayoutInflater, ViewGroup?) -> Bind
) : Fragment(), BindLife {

    constructor(clazz: Class<VM>, @LayoutRes layoutRes: Int) : this(clazz, { inflater, group ->
        DataBindingUtil.inflate(inflater, layoutRes, group, false)
    })

    val viewModel: VM by lazy {
        ViewModelProviders.of(
            this
            //,ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
        ).get(clazz)
    }

    lateinit var binding: Bind

    override val compositeDisposable = CompositeDisposable()


    //create view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = bindingCreator.invoke(layoutInflater, container)
        return binding.root
    }


    //do when view created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBefore()
        initWidget(view)
        initData()
    }


    //something init before initWidget
    abstract fun initBefore()

    //widget init
    abstract fun initWidget(view: View)

    //data init
    abstract fun initData()



    //Fragment's destroy function
    override fun onDestroy() {
        destroyDisposable()
        super.onDestroy()
    }

    //hide keyBoard
    fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        if (imm!!.isActive)
            imm.hideSoftInputFromWindow(
                (context as Activity).currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
    }



}