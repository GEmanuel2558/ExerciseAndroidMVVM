package gresanu.emanuel.vasile.project

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import gresanu.emanuel.vasile.project.di.component.AppComponent
import gresanu.emanuel.vasile.project.di.component.DaggerAppComponent

class MyApp: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val app: AppComponent = DaggerAppComponent.builder().application(this).build()
        app.inject(this)
        return app
    }

    override fun onCreate() {
        super.onCreate()
    }
}