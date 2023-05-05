package com.example.learningroomandretrofit.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Update
    suspend fun updatePerson(person: Person)

    @Query("SELECT * FROM Person WHERE id=:id")
    suspend fun getPersonById(id: Int): Person

    @Query("SELECT * FROM Person")
    fun getAll(): Flow<List<Person>>
}
