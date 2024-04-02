package com.application.dripjoycoffee

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val confirmpassword: String
)
