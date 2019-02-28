package com.krinitsyn.git_gist

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.krinitsyn.utils.logger.EmptyLogger
import com.krinitsyn.utils.logger.LogcatLogger
import io.reactivex.Scheduler
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

internal object GithubGistRetrofitProvider {

    private const val Tag = "GithubGistRetrofit"
    private const val BaseUrl = "https://api.github.com/"

    fun provideRetrofit(scheduler: Scheduler): Retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .client(provideOkHttpClient())
        .addCallAdapterFactory(provideRxJava2CallAdapterFactory(scheduler))
        .addConverterFactory(provideConverterFactory())
        .build()

    private fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(provideHttpLoggingInterceptor())
        .connectTimeout(10L, TimeUnit.SECONDS)
        .writeTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .build()

    private fun provideHttpLoggingInterceptor(): Interceptor {
        val logger = if (BuildConfig.DEBUG) LogcatLogger() else EmptyLogger()
        val httpLogger = HttpLoggingInterceptor.Logger { message -> logger.d(Tag, message, null) }
        return HttpLoggingInterceptor(httpLogger).setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun provideRxJava2CallAdapterFactory(scheduler: Scheduler): CallAdapter.Factory = RxJava2CallAdapterFactory
        .createWithScheduler(scheduler)

    private fun provideConverterFactory(): Converter.Factory = JacksonConverterFactory
        .create(provideObjectMapper())

    private fun provideObjectMapper(): ObjectMapper = ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

}