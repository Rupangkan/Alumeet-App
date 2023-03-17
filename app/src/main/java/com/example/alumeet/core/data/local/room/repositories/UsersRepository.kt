package com.example.alumeet.core.data.local.room.repositories

import com.example.alumeet.core.data.local.room.dao.UsersDao
import com.example.alumeet.core.data.model.local.User
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersDao: UsersDao
) {
    suspend fun getUser(email: String, password: String): List<User> {
        return usersDao.getUser(email, password)
    }

    suspend fun insertUser(user: User) {
        usersDao.insertUser(user)
    }
}