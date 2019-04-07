package gresanu.emanuel.vasile.project.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import gresanu.emanuel.vasile.project.network.GeneralInformation
import retrofit2.Retrofit

@Module
object NetworkApiModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): GeneralInformation {
        return retrofit.create(GeneralInformation::class.java)
    }

}