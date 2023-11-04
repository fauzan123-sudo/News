package com.example.news.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.news.util.Constans.NOTIFICATION_TABLE

@Entity(tableName = NOTIFICATION_TABLE)
data class Notification(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title:String? = "",
    var body:String? = "",
    var isRead: Boolean = false,
    var timestamp: Long = System.currentTimeMillis()
)