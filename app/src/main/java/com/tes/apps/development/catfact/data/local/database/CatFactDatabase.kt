package com.tes.apps.development.catfact.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tes.apps.development.catfact.data.local.dao.CatFactDao
import com.tes.apps.development.catfact.data.local.entity.CatFactEntity

@Database(
    entities = [CatFactEntity::class],
    version =5,
    exportSchema = false
)
abstract class CatFactDatabase:RoomDatabase() {
    abstract fun catFactDao():CatFactDao
}