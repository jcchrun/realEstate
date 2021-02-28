package com.jcchrun.real.remote.di

import com.jcchrun.real.remote.BuildConfig
import com.jcchrun.real.remote.api.ApiServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val remoteModule = module {

    factory { buildApiServices() }
}

private fun buildOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        builder.addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        })
    }
    return builder.build()
}

private fun buildApiServices() = Retrofit.Builder()
    .client(buildOkHttpClient())
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create(ApiServices::class.java)