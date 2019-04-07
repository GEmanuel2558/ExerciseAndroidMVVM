package gresanu.emanuel.vasile.project.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import gresanu.emanuel.vasile.project.MyApp
import gresanu.emanuel.vasile.project.di.builder.ActivityBuilder
import gresanu.emanuel.vasile.project.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent: AndroidInjector<DaggerApplication> {

    fun inject(app: MyApp)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface MyCustomBuilder {

        @BindsInstance
        fun application(application: Application): MyCustomBuilder

        fun build(): AppComponent
    }
}