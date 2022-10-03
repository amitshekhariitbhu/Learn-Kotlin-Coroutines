package me.amitshekhar.learn.kotlin.coroutines.data.api

import me.amitshekhar.learn.kotlin.coroutines.data.model.ApiUser

interface ApiHelper {

    suspend fun getUsers(): List<ApiUser>

    suspend fun getMoreUsers(): List<ApiUser>

    suspend fun getUsersWithError(): List<ApiUser>

}