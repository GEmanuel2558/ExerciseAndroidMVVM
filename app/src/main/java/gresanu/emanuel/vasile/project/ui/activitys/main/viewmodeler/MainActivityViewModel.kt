package gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler

import android.arch.lifecycle.AndroidViewModel
import android.os.Bundle
import dagger.android.DaggerApplication
import gresanu.emanuel.vasile.project.MyApp
import gresanu.emanuel.vasile.project.di.module.NetworkInitModule
import gresanu.emanuel.vasile.project.network.GeneralInformation
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityViewModel(application: DaggerApplication): AndroidViewModel(application) {

    var isNewlyCreated = true
    private val disposable = CompositeDisposable()

    @Inject
    lateinit var networkCall: GeneralInformation

    init {
        getApplication<MyApp>().app.dependecySubcomponent(NetworkInitModule).inject(this)
    }

    fun onSaveInstance(outState: Bundle) {

    }

    fun onRestoreState(savedInstance: Bundle) {

    }

    fun loadData(){
/*        Observable.fromCallable {

        }*/

        //Observable.amb(mutableListOf())
    }
}