package com.application.dripjoycoffee

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://skinship.shop/?fbclid=IwAR2LT7nfvmIVD1WRnt0ncyharf-NzhUJcqn1s22B9_PhpVKVHURlQRj08Nw_aem_AQ5QQ2huoB0YT3Hz6pFOtQErK-UkU7icK3JDv5Nb5A61kDe1jh_46DgBS0tZ-jdih_91cD1_O9dFJL4h8IsXIj6l"
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor{chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(ApiService::class.java)
    }
}