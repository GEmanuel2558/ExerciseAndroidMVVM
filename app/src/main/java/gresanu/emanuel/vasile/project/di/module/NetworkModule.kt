package gresanu.emanuel.vasile.project.di.module

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable
import gresanu.emanuel.vasile.project.constants.MyConstants.BASE_REQUEST_URL
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    //Allocate 1 Mb for the cache
    @JvmStatic
    @Singleton
    @Provides
    internal fun privideCache(context:Context) = Cache(context.cacheDir, (1024*1024))

    @JvmStatic
    @Singleton
    @Provides
    internal fun provideNetworkInterceptor(): HttpLoggingInterceptor {
        val myCustomInterceptor = HttpLoggingInterceptor()
        myCustomInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return myCustomInterceptor
    }

    @JvmStatic
    @Singleton
    @Provides
    internal fun provideOkHttpClient(cache: Cache, interceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .cache(cache)
        .build() as OkHttpClient

    @JvmStatic
    @Singleton
    @Provides
    internal fun provideAdapterFactory() = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()) as CallAdapter.Factory

    @JvmStatic
    @Singleton
    @Provides
    internal fun provideGsonFactory() = GsonConverterFactory.create(Gson()) as Converter.Factory

    @JvmStatic
    @Reusable
    @Provides
    internal fun proviceRetrofitInterface(convertFactory: Converter.Factory, adapterFactory: CallAdapter.Factory, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_REQUEST_URL)
            .addConverterFactory(convertFactory)
            .addCallAdapterFactory(adapterFactory)
            .client(client)
            .build()
    }

}