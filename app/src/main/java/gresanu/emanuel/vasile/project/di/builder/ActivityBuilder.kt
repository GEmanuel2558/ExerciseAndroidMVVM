package gresanu.emanuel.vasile.project.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import gresanu.emanuel.vasile.project.di.annotations.PerActivity
import gresanu.emanuel.vasile.project.ui.activitys.MainActivity
import gresanu.emanuel.vasile.project.ui.activitys.MainActivityProviders

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MainActivityProviders::class))
    abstract fun bindWithMainActivity(): MainActivity

}