package com.fintech.denispok.homework1

import android.app.IntentService
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DateIntentService : IntentService("date_service") {

    override fun onHandleIntent(intent: Intent?) {
        val pattern = intent?.getStringExtra("pattern") ?: ""
        val timeout = intent?.getIntExtra("timeout", 0)?.toLong() ?: 0

        val dateFormat = SimpleDateFormat(pattern)
        val date = dateFormat.format(Calendar.getInstance().time)

        TimeUnit.SECONDS.sleep(timeout)

        val responseIntent = Intent("formatted_date").putExtra("date", date)
        LocalBroadcastManager.getInstance(baseContext).sendBroadcast(responseIntent)
    }
}