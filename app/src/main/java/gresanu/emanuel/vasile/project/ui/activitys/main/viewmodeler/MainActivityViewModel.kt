package gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import dagger.android.DaggerApplication
import gresanu.emanuel.vasile.project.MyApp
import gresanu.emanuel.vasile.project.database.MyDataBase
import gresanu.emanuel.vasile.project.di.module.NetworkInitModule
import gresanu.emanuel.vasile.project.network.GeneralInformation
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityViewModel(application: DaggerApplication): AndroidViewModel(application) {

    var isNewlyCreated = true
    private val disposable = CompositeDisposable()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    @Inject
    lateinit var networkCall: GeneralInformation

    init {
        getApplication<MyApp>().app.dependecySubcomponent(NetworkInitModule).inject(this)
        loadData()
    }

    fun onSaveInstance(outState: Bundle) {

    }

    fun onRestoreState(savedInstance: Bundle) {

    }

    fun loadData(){
        val currentDb = MyDataBase.getCurrentDb(getApplication())
        val network = networkCall.getAllMessages().doOnNext { listOfMessages ->
            currentDb?.bind()?.let {dao->
                listOfMessages.forEach {
                    dao.insertAll(it)
                }
            }
        }
        val disk = Observable.fromCallable {
            currentDb?.bind()?.getAll
        }
        val onlyTheResponse = Observable.concat(network,disk).first(listOf())
    }
}