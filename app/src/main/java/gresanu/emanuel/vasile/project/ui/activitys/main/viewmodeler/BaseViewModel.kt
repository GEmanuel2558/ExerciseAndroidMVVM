package gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler

import android.arch.lifecycle.ViewModel
import gresanu.emanuel.vasile.project.di.component.DaggerViewModelInjector
import gresanu.emanuel.vasile.project.di.module.NetworkApiModule
import gresanu.emanuel.vasile.project.di.module.NetworkInitModule

abstract class BaseViewModel: ViewModel() {

    init {
        initInjection()
    }

    private fun initInjection() {
        val injectionPoint = DaggerViewModelInjector.builder().addNetworkInitModule(NetworkInitModule).build()
        when (this) {
            is MainActivityViewModel -> injectionPoint.inject(this)
            is RecyclerItemViewModel -> injectionPoint.inject(this)
        }
    }

}