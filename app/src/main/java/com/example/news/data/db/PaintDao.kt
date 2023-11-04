package com.example.news.data.db

import androidx.room.Dao
import androidx.room.Delete

@Dao
interface PaintDao {

//    @Insert("Insert into from *  Notification")
//    suspend fun saveData() {
//
//    }

    @Delete
    suspend fun deleteata(id:Int){

    }
}