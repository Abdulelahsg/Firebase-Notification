package com.example.notifications.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.notifications.R
import com.example.notifications.util.sendNotification

class AlarmReceiver: BroadcastReceiver(){
    override fun onReceive(context: Context,intent: Intent) {
        //Toast.makeText(context, context.getText(R.string.eggs_ready), Toast.LENGTH_SHORT).show()

        // add call to sendNotification
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
            context.getText(R.string.eggs_ready).toString(),
            context
        )
    }
}