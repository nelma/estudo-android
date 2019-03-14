package com.example.estudoandroid

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    //ref const
    companion object {
        const val TAG = "FMService"
    }

    override fun onNewToken(token: String?) {
        Log.i(TAG, token)

        val firebaseMessaging = FirebaseMessaging.getInstance();
        firebaseMessaging.subscribeToTopic("Main")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val notification = remoteMessage.notification

        Log.i(TAG, "FCM Notification Message: $notification")
        Log.i(TAG, "FCM Message ID: ${remoteMessage.messageId}")
        Log.i(TAG, "FCM Message DATA: ${remoteMessage.data}")

        notification?.let {
            val title = it.title ?: ""
            val body = it.body ?: ""

            Log.i(TAG, "FCM Notification title $title")
            Log.i(TAG, "FCM Notification body $body")

            NotificationCreation.create(this, title, body)
        }
    }
}
