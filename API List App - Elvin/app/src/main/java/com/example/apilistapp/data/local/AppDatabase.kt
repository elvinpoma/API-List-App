package com.example.apilistapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apilistapp.data.local.dao.RMCharacterDao
import com.example.apilistapp.data.local.entity.Converters
import com.example.apilistapp.data.local.entity.RMCharacterEntity

@Database(entities = arrayOf(RMCharacterEntity::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun characterDao(): RMCharacterDao
}
