package com.application.dripjoycoffee

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val confirmpassword: String,
    val updated_at: String,
    val created_at: String,
)
