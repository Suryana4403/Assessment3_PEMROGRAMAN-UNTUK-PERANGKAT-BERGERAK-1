package org.d3if6706202120.assessment1.network

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.d3if6706202120.assessment1.MainActivity
import org.d3if6706202120.assessment1.R
import org.d3if6706202120.assessment1.ui.main.BangunDatarFragment

class UpdateWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val notificationId = 44
    override fun doWork(): Result {

        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)


        val builder = NotificationCompat.Builder(applicationContext,
            MainActivity.CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(applicationContext.getString(
                R.string.notif_title))
            .setContentText(applicationContext.getString(
                R.string.notif_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val manager = NotificationManagerCompat.from(applicationContext)
        manager.notify(notificationId, builder.build())




        if(snycNewsData())
            return Result.success()
        else
            return Result.failure()
    }
    fun snycNewsData(): Boolean {
return true
    }
}
