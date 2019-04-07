package gresanu.emanuel.vasile.project.ui.activitys.main

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides

@Module
abstract class MainActivityProviders {

    //@Binds
    //abstract fun provideInternalAndPhoneMemory(mainView: MainActivity): MainActivity

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideRecylerLayout(context: Context) = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}