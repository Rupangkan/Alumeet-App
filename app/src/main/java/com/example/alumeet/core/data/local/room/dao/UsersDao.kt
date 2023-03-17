package com.example.alumeet.core.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alumeet.core.data.model.local.User

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(acceptedRide: User)

    @Query("select * from users where password=:password and email=:email")
    suspend fun getUser(email: String, password: String): List<User>

}