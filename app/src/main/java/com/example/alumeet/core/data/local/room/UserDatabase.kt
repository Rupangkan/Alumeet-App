package com.example.alumeet.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alumeet.core.data.local.room.dao.UsersDao
import com.example.alumeet.core.data.model.local.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUsersDao(): UsersDao
}