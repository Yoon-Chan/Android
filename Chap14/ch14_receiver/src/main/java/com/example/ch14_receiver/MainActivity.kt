package com.example.test14

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ch14_receiver.MyReceiver
import com.example.ch14_receiver.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val intent = Intent(this, MyReceiver::class.java)
            sendBroadcast(intent)
        }

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d("doit","registerReceiver... onReceive..........")
            }
        }
        val filter = IntentFilter("ACTION_RECEIVER")
        registerReceiver(receiver, filter)

        binding.button2.setOnClickListener {
            val intent = Intent("ACTION_RECEIVER")
            sendBroadcast(intent)
        }


        registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))!!.apply {
            when(getIntExtra(BatteryManager.EXTRA_STATUS, -1)){
                BatteryManager.BATTERY_STATUS_CHARGING -> {
                    when(getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)){
                        BatteryManager.BATTERY_PLUGGED_USB ->{
                            binding.
                        }
                    }
                }
            }
        }
    }
}