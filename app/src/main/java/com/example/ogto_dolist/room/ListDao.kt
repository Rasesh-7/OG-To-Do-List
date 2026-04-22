package com.example.ogto_dolist.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {

    @Insert
    suspend fun addList(listEntity: ListEntity)

    @Query("SELECT * FROM ListEntity")
    fun getList() : Flow<List<ListEntity>>

    @Delete
    suspend fun deleteList(listEntity: ListEntity)

    @Update
    suspend fun updateList(listEntity: ListEntity)
}