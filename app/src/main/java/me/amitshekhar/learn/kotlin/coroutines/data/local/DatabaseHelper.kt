package me.amitshekhar.learn.kotlin.coroutines.data.local

import me.amitshekhar.learn.kotlin.coroutines.data.local.entity.User

interface DatabaseHelper {

    suspend fun getUsers(): List<User>

    suspend fun insertAll(users: List<User>)

}