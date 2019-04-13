package gresanu.emanuel.vasile.project.di.component

import dagger.Subcomponent
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

}