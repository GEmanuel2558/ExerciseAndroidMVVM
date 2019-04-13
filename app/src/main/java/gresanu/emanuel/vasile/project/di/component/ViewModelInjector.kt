package gresanu.emanuel.vasile.project.di.component

import dagger.Reusable
import dagger.Subcomponent
import gresanu.emanuel.vasile.project.di.annotations.PerActivity
import gresanu.emanuel.vasile.project.di.annotations.PerChildren
import gresanu.emanuel.vasile.project.di.module.NetworkApiModule
import gresanu.emanuel.vasile.project.di.module.NetworkInitModule
import gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler.MainActivityViewModel
import gresanu.emanuel.vasile.project.ui.activitys.main.viewmodeler.RecyclerItemViewModel

@PerChildren
@Subcomponent(modules = [NetworkInitModule::class, NetworkApiModule::class])
interface ViewModelInjector {

    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun inject(recyclerItemViewModel: RecyclerItemViewModel)

/*    @Component.Builder
    interface MyCustomBuilder {

        fun build(): ViewModelInjector

        @BindsInstance
        fun addNetworkInitModule(networkInit: NetworkInitModule):MyCustomBuilder

        @BindsInstance
        fun application(application: Application): MyCustomBuilder

    }*/

}