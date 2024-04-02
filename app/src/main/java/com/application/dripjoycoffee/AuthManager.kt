package com.application.dripjoycoffee

class AuthManager {
    var authToken: String? = null
        private set
    private var userId: Int? = null

    companion object {
        val instance by lazy { AuthManager() }
    }

    fun setAuthToken(token: String) {
        authToken = token
    }

    fun getUserId(): Int? {
        return userId
    }

    fun setUserId(id: Int) {
        userId = id
    }

    fun clearAuthToken() {
        authToken = null
    }

    fun clearUserId() {
        userId = null
    }
}