package com.example.test_outter

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return object : IMyAidlInterface.Stub(){
            override fun funB(): Int {
                TODO("Not yet implemented")
            }

            override fun funA(data: String?) {
                TODO("Not yet implemented")
            }
        }
    }
}