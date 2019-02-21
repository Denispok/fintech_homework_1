package com.fintech.denispok.homework1

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var reciever: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        reciever = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        LocalBroadcastManager.getInstance(baseContext).registerReceiver(reciever, IntentFilter("formatted_date"))

        val intent = Intent(this, DateIntentService::class.java)
        startService(intent.putExtra("pattern", "EEEE").putExtra("timeout", 3))
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(baseContext).unregisterReceiver(reciever)
    }
}