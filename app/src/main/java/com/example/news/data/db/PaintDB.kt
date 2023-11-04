package com.example.news.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.data.model.Notification

@Database(entities = [Notification::class], version = 2, exportSchema = false)
abstract class PaintDB : RoomDatabase() {
    abstract fun contactsDao(): PaintDao
}