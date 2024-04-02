package com.application.dripjoycoffee

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("api/register")
    fun registerUser(
        @Field("username") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") confirmpassword: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("api/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}