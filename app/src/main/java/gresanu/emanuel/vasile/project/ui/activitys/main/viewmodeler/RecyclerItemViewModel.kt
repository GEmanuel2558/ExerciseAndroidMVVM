package gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler

import android.arch.lifecycle.MutableLiveData

data class RecyclerItemViewModel(val title: MutableLiveData<String> = MutableLiveData(),
                            val message: MutableLiveData<String> = MutableLiveData()): BaseViewModel()