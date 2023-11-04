package com.example.news.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.news.R
import com.example.news.data.repository.NotificationRepository
import com.example.news.ui.activity.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    //    private lateinit var adapter: NotificationAdapter
    private lateinit var repository: NotificationRepository

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FirebaseToken", "Refreshed token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val channelId = "my_channel_id"
        val channelName = "My Channel"
        val channelDescription = "Channel untuk notifikasi dari firebase"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            channel.description = channelDescription
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Buat intent untuk membuka aplikasi
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // Buat notifikasi
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(remoteMessage.data["title"] ?: "Notifikasi")
            .setContentText(remoteMessage.data["message"] ?: "Pesan dari firebase")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        // Tampilkan notifikasi
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification)
    }

}

//    private fun saveNotification(title: String, body: String) {
//        val contactsEntity = Notification(
//            title = title,
//            body = body,
//            isRead = false,
//            timestamp = System.currentTimeMillis()
//        )
//
//        val databaseRepository = NotificationRepository(provideDao(applicationContext))
//        CoroutineScope(Dispatchers.IO).launch {
//            databaseRepository.saveContact(contactsEntity)
//        }
//
//        val notificationData = NotificationData(title, body)
//        val notifications = Paper.book().read(Constans.notify, mutableListOf<NotificationData>())
//        Log.d("data", "$notifications")
//        notifications!!.add(notificationData)
//        Paper.book().write(Constans.notify, notifications)
////        adapter.setNotifications(notifications)
////        adapter.notifyDataSetChanged()
//    }
//
//    private fun showNotification(title: String?, message: String?) {
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channelId = "channel_id"
//            val channelName = "channel_name"
//            val channel =
//                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        val pendingIntent = PendingIntent.getActivity(
//            this, 0, intent,
//            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//        )
//
//        val notificationBuilder = NotificationCompat.Builder(this, "channel_id")
//            .setContentTitle(title)
//            .setContentText(message)
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setAutoCancel(true)
//            .setContentIntent(pendingIntent)
//
//        notificationManager.notify(123, notificationBuilder.build())
//    }
//
//    private fun provideDao(context: Context): PaintDao {
//        val database = Room.databaseBuilder(
//            context, PaintDB::class.java, CONTACTS_DATABASE
//        ).build()
//        return database.contactsDao()
//    }


