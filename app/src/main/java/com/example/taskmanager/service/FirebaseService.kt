package com.example.taskmanager.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.taskmanager.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage



class FirebaseService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        showNotification(message)
        Log.e("ololo", "onMassageReceived: " + message.notification?.title)
        Log.e("ololo", "onMassageReceived: " + message.notification?.body)

    }

    private fun showNotification(message: RemoteMessage) {
        val notificationBuilder = NotificationCompat.Builder(this, "Task_channelId")
        notificationBuilder.setSmallIcon(R.drawable.ic_notifications_black_24dp)
        notificationBuilder.setContentTitle(message.notification?.title)
        notificationBuilder.setContentText(message.notification?.body)
        notificationBuilder.setAutoCancel(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            "Task_channelId",
            "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        notificationManager.notify(1, notificationBuilder.build())
    }
}