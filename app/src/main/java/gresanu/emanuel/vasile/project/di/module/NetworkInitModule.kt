package gresanu.emanuel.vasile.project.di.module

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.telephony.TelephonyManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import gresanu.emanuel.vasile.project.constants.MyConstants.BASE_REQUEST_URL
import gresanu.emanuel.vasile.project.di.annotations.PerChildren
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
object NetworkInitModule {

    const val MAX_PREFETCH_CACHE = (1024 * 1024).toLong()
    const val DEFAULT_PREFETCH_CACHE = 1024L

    //Allocate 1 Mb for the cache
    @JvmStatic
    @PerChildren
    @Provides
    internal fun privideCache(context: Context): Cache {

        val prefetchCacheSize = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            PackageManager.PERMISSION_GRANTED == context.applicationContext.checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)
        ) {
            //https://developer.android.com/training/efficient-downloads/connectivity_patterns.html?authuser=3&hl=ru
            val cm = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val tm = context.applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        MAX_PREFETCH_CACHE
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        getCacheSizeForMobileDataConnection(tm)
                    }
                    else -> DEFAULT_PREFETCH_CACHE
                }
            } else {
                when (activeNetwork?.type) {
                    ConnectivityManager.TYPE_WIFI -> MAX_PREFETCH_CACHE
                    ConnectivityManager.TYPE_MOBILE -> {
                        getCacheSizeForMobileDataConnection(tm)
                    }
                    else -> DEFAULT_PREFETCH_CACHE
                }
            }
        } else {
            DEFAULT_PREFETCH_CACHE
        }
        return Cache(context.cacheDir, prefetchCacheSize)
    }

    private fun getCacheSizeForMobileDataConnection(tm: TelephonyManager): Long {
        return when (tm.networkType) {
            TelephonyManager.NETWORK_TYPE_LTE or TelephonyManager.NETWORK_TYPE_HSPAP ->
                DEFAULT_PREFETCH_CACHE * 4
            TelephonyManager.NETWORK_TYPE_EDGE or TelephonyManager.NETWORK_TYPE_GPRS ->
                DEFAULT_PREFETCH_CACHE / 2
            else -> DEFAULT_PREFETCH_CACHE
        }
    }

    @JvmStatic
    @PerChildren
    @Provides
    internal fun provideNetworkInterceptor(): HttpLoggingInterceptor {
        val myCustomInterceptor = HttpLoggingInterceptor()
        myCustomInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return myCustomInterceptor
    }

    @JvmStatic
    @PerChildren
    @Provides
    internal fun provideOkHttpClient(cache: Cache, interceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .cache(cache)
        .build() as OkHttpClient

    @JvmStatic
    @PerChildren
    @Provides
    internal fun provideAdapterFactory() =
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()) as CallAdapter.Factory

    @JvmStatic
    @PerChildren
    @Provides
    internal fun provideGsonFactory() = GsonConverterFactory.create(Gson()) as Converter.Factory

    @JvmStatic
    @PerChildren
    @Provides
    internal fun proviceRetrofitInterface(
        convertFactory: Converter.Factory,
        adapterFactory: CallAdapter.Factory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_REQUEST_URL)
            .addConverterFactory(convertFactory)
            .addCallAdapterFactory(adapterFactory)
            .client(client)
            .build()
    }

}