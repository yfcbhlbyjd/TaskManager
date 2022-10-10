package com.example.taskmanager.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.taskmanager.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.NotificationParams



class FirebaseService : FirebaseMessagingService() {

    override fun onMessageReceived(massage: RenoteMessage) {
        super.onMessageReceived(message)
        showNotification(massage)
        log.e("ololo", "onMassageReceived: " + massage.notification?.title)
        log.e("ololo", "onMassageReceived: " + massage.notification?.body)

    }

    private fun showNotification(massage: Any) {
        val notificationBuilder = NotificationCompat.Builder(this, "Task_channelId")
        notificationBuilder.setSmallIcon(R.drawable.ic_notifications_black_24dp)
        notificationBuilder.setContentTitle(massage.notification?.title)
        notificationBuilder.setContentText(massage.notification?.body)
        notificationBuilder.setAutoCancel(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel (
            "Task_channelId",
            "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(1, notificationBuilder.build())
    }
}