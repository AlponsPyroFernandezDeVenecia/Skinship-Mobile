package com.application.dripjoycoffee

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val confirmpassword: String,
)
