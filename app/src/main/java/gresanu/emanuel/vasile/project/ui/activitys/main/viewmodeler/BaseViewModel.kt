package gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler

import android.arch.lifecycle.ViewModel
import gresanu.emanuel.vasile.project.di.component.DaggerViewModelInjector
import gresanu.emanuel.vasile.project.di.module.NetworkModule

abstract class BaseViewModel: ViewModel() {

    init {
        initInjection()
    }

    private fun initInjection() {
        val injectionPoint = DaggerViewModelInjector.builder().addNetworkModule(NetworkModule).build()
        when (this) {
            is MainActivityViewModel -> injectionPoint.inject(this)
            is RecyclerItemViewModel -> injectionPoint.inject(this)
        }
    }

}