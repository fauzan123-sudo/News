package com.example.news.data.model

data class NotificationData(
    val title: String?,
    val body: String?,
    var isRead: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)