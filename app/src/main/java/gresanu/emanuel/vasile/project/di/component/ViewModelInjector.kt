package gresanu.emanuel.vasile.project.di.component

import dagger.BindsInstance
import dagger.Component
import gresanu.emanuel.vasile.project.di.module.NetworkModule
import gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler.MainActivityViewModel
import gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler.RecyclerItemViewModel


@Component(modules = [NetworkModule::class])
interface ViewModelInjector {

    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun inject(recyclerItemViewModel: RecyclerItemViewModel)

    @Component.Builder
    interface MyCustomBuilder {

        fun build(): ViewModelInjector

        @BindsInstance
        fun addNetworkModule(network: NetworkModule):MyCustomBuilder
    }

}