package com.demo.android.k_crud.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDao {

    // Create
    @Insert
    suspend fun insert(person: Person)

    // Read
    @Query("SELECT * FROM person ORDER BY id DESC")
    fun getAllPerson(): LiveData<List<Person>>

    // Update
    @Update
    suspend fun update(person: Person)

    // Delete
    @Delete
    suspend fun delete(person: Person)

    // Clear
    @Query("DELETE FROM person")
    suspend fun clear();

    // Get item by id
    @Query("SELECT * FROM person WHERE id = :key")
    fun getPersonById(key: Long): LiveData<Person>

    // Find item by id
    @Query("SELECT * FROM person WHERE id = :key")
    suspend fun findPersonById(key: Long): Person
}