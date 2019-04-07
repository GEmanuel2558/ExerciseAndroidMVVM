package gresanu.emanuel.vasile.project.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import gresanu.emanuel.vasile.project.di.annotations.PerActivity
import gresanu.emanuel.vasile.project.ui.activitys.main.MainActivity
import gresanu.emanuel.vasile.project.ui.activitys.main.MainActivityProviders

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityProviders::class))
    @PerActivity
    abstract fun bindWithMainActivity(): MainActivity

}