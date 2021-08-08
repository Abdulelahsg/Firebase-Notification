package com.example.notifications.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.example.notifications.MainActivity
import com.example.notifications.R
import com.example.notifications.receiver.SnoozeReceiver

// Notification ID
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

/**
 * Builds and delivers the notification.
 *
 *@param context, activity context.
 */

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context){
    // Create the content intent for the notification, which launches
    // this activity
    val contestIntent = Intent(applicationContext, MainActivity::class.java)
    // Pending Intent
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contestIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    // Add style
    val eggImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.ready_notification
    )
    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage)
        .bigLargeIcon(null)

    // Add snooze action

    val snoozeIntent =  Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(
        applicationContext,
        REQUEST_CODE,
        snoozeIntent,
        FLAGS)

    // Get an instance of NotificationCompat.Builder
    // Build the notification
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.egg_notification_channel_id)
    )

    // Use the new 'breakfast' notification channel
    // set title, text and icon to builder
        .setSmallIcon(R.drawable.ready_notification)
        .setContentTitle(applicationContext
            .getString(R.string.notification_title))
        .setContentText(messageBody)

    // set content intent
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)


    // add style to builder
        .setStyle(bigPicStyle)
        .setLargeIcon(eggImage)


    // add snooze action
        .addAction(
            R.drawable.egg_icon,
            applicationContext.getString(R.string.snooze),
            snoozePendingIntent
        )

    // set priority
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    // call notify
    notify(NOTIFICATION_ID, builder.build())

}

// cancels all notifications

fun NotificationManager.cancelNotifications(){
    cancelAll()
}