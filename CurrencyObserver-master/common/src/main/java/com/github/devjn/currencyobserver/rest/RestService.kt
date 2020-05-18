package com.github.devjn.currencyobserver.rest

import android.util.Log
import com.github.devjn.currencyobserver.utils.NativeUtils
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


/**
 * Created by @author Jahongir on 01-Mar-18
 * devjn@jn-arts.com
 * RestService
 */
object RestService {

    const val CURRENCY_BASE_URL = "https://api.fixer.io/"
    const val CRYPTO_BASE_URL = "https://api.coinmarketcap.com/"

    private val CACHE_CONTROL = "Cache-Control"
    private val client: OkHttpClient

    var BASE_URL = CURRENCY_BASE_URL
        internal set

    private var builder: Retrofit.Builder

    init {
        Log.i("RestService", "init")
        //setup cache
        val httpCacheDirectory = File(NativeUtils.resolver.cacheDir, "responses")
        val cacheSize = 10L * 1024 * 1024 // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        //set cache to the client
        client = OkHttpClient.Builder()
                .sslSocketFactory(getSSLSocketFactory())
                .hostnameVerifier { hostname: String, sslSession: SSLSession ->
                    true
                }
                .addInterceptor(provideOfflineCacheInterceptor())
                .addInterceptor(interceptor)
//                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .addNetworkInterceptor(provideCacheInterceptor())
                .cache(cache)
                .build()


        RestService.builder = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

    private fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            // re-write response header to force use of cache
            val cacheControl = CacheControl.Builder()
                    .maxAge(1, TimeUnit.MINUTES)
                    .build()

            response.newBuilder()
                    .header(CACHE_CONTROL, cacheControl.toString())
                    .build()
        }
    }

    private fun provideOfflineCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            if (!NativeUtils.resolver.isNetworkAvailable()) {
                val cacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()

                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build()
            }

            chain.proceed(request)
        }
    }

    @JvmStatic
    fun changeApiBaseUrl(newApiBaseUrl: String) {
        BASE_URL = newApiBaseUrl

        builder = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }


    fun getCurrencyService(): RestApiCurrency {
        return builder.baseUrl(CURRENCY_BASE_URL).build().create(RestApiCurrency::class.java)
    }

    fun getCryptoCurrencyService(): RestApiCrypto {
        return builder.baseUrl(CRYPTO_BASE_URL).build().create(RestApiCrypto::class.java)
    }


    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }


    private fun getSSLSocketFactory(): SSLSocketFactory? {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager

            return sslContext.getSocketFactory()
        } catch (e: KeyManagementException) {
            return null
        } catch (e: NoSuchAlgorithmException) {
            return null
        }

    }

}