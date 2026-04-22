package com.example.ogto_dolist.repository

import com.example.ogto_dolist.room.ListDb
import com.example.ogto_dolist.room.ListEntity

class Repository(val listDb: ListDb) {

    suspend fun addListToRoom(listEntity: ListEntity){
        listDb.getDao().addList(listEntity)
    }

    fun getList() = listDb.getDao().getList()

    suspend fun deleteListFromRoom(listEntity: ListEntity){
        listDb.getDao().deleteList(listEntity)
    }

    suspend fun updateListToRoom(listEntity: ListEntity){
        listDb.getDao().updateList(listEntity)
    }
}