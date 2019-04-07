package gresanu.emanuel.vasile.project.di.component

import dagger.BindsInstance
import dagger.Component
import gresanu.emanuel.vasile.project.di.module.NetworkApiModule
import gresanu.emanuel.vasile.project.di.module.NetworkInitModule
import gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler.MainActivityViewModel
import gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler.RecyclerItemViewModel


@Component(modules = [NetworkInitModule::class, NetworkApiModule::class])
interface ViewModelInjector {

    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun inject(recyclerItemViewModel: RecyclerItemViewModel)

    @Component.Builder
    interface MyCustomBuilder {

        fun build(): ViewModelInjector

        @BindsInstance
        fun addNetworkInitModule(networkInit: NetworkInitModule):MyCustomBuilder

    }

}