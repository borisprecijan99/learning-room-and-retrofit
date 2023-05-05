package com.example.learningroomandretrofit.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String? = null,
    val lastName: String? = null
)
