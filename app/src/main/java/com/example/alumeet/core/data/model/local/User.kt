package com.example.alumeet.core.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var occupation: String = " +",
    var email: String = "",
    var password: String = ""
)