package com.example.ogto_dolist.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListEntity::class], version = 1, exportSchema = false)
abstract class ListDb: RoomDatabase(){

    abstract fun getDao(): ListDao

    companion object{

        @Volatile
        private var INSTANCE : ListDb? = null

        fun getDatabase(context: Context): ListDb{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    ListDb::class.java,
                    "to_dolist"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}