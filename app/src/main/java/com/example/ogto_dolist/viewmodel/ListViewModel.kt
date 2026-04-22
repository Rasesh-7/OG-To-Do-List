package com.example.ogto_dolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ogto_dolist.repository.Repository
import com.example.ogto_dolist.room.ListEntity
import kotlinx.coroutines.launch

class ListViewModel(val repository: Repository): ViewModel() {

    fun addList(listEntity: ListEntity){
        viewModelScope.launch {
            repository.addListToRoom(listEntity)
        }
    }

    val lists = repository.getList()

    fun updateList(listEntity: ListEntity){
        viewModelScope.launch {
            repository.updateListToRoom(listEntity)
        }
    }

    fun deleteList(listEntity: ListEntity){
        viewModelScope.launch {
            repository.deleteListFromRoom(listEntity)
        }
    }
}