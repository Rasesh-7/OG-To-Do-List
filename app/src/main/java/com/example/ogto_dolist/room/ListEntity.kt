package com.example.ogto_dolist.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val title: String
)
