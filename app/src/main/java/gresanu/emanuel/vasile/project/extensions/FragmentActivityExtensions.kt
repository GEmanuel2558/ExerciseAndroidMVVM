package gresanu.emanuel.vasile.project.extensions

import android.arch.lifecycle.*
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

inline fun <reified T : ViewModel> getViewModel(activity: AppCompatActivity, factory: ViewModelProvider.Factory? = null): T {
    return ViewModelProviders.of(activity, factory)[T::class.java]
}

inline fun <reified T : ViewModel> getViewModel(fragment: Fragment, factory: ViewModelProvider.Factory? = null): T {
    return ViewModelProviders.of(fragment, factory)[T::class.java]
}


inline fun <reified T : ViewModel> AppCompatActivity.withViewModel(body: T.() -> Unit): T {
    val vm = getViewModel<T>(this)
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> Fragment.withViewModel(body: T.() -> Unit): T {
    val vm = getViewModel<T>(this)
    vm.body()
    return vm
}


fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

inline fun <reified T : ViewModel> AppCompatActivity.withViewModel(
    crossinline factory: () -> T,
    body: T.() -> Unit
): T {
    val vm = getViewModel(factory)
    vm.body()
    return vm
}

@SuppressWarnings("unchecked")
inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProviders.of(this, vmFactory)[T::class.java]
}


inline fun <reified T : ViewModel> Fragment.withViewModel(
    crossinline factory: () -> T,
    body: T.() -> Unit
): T {
    val vm = getViewModel(factory)
    vm.body()
    return vm
}

@SuppressWarnings("unchecked")
inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProviders.of(this, vmFactory)[T::class.java]
}