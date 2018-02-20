package com.acm.verywild.acm.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v7.app.NotificationCompat
import android.util.Log
import com.acm.verywild.acm.MainActivity
import com.acm.verywild.acm.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {

    private val TAG = "MyFirebaseMsgService"

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        var notificationTitle: String? = null
        var notificationBody: String? = null
        var dataTitle: String? = null
        var dataMessage: String? = null

        Log.d(TAG, "From: " + remoteMessage?.from)

        if (remoteMessage?.data?.size ?: 0 > 0) {
            Log.d(TAG, "Message data payload: " + (remoteMessage?.data?.get("message") ?: null))
            dataTitle = remoteMessage?.data?.get("title")
            dataMessage = remoteMessage?.data?.get("message")
        }

        // Check if message contains a notification payload.
        remoteMessage?.let {
            Log.d(TAG, "Message Notification Body: " + it.notification.body)
            notificationTitle = it.notification.title
            notificationBody = it.notification.body
        }

        sendNotification(notificationTitle, notificationBody, dataTitle, dataMessage)
    }

    private fun sendNotification(notificationTitle: String?, notificationBody: String?, dataTitle: String?, dataMessage: String?) {

        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notificationTitle)
                .setContentText(notificationBody)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(PendingIntent.getActivity(this
                        , 0 /* Request code */
                        , Intent(this, MainActivity::class.java).apply {
                    putExtra("title", dataTitle)
                    putExtra("message", dataMessage)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                        , PendingIntent.FLAG_ONE_SHOT)) as NotificationCompat.Builder

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }
}