package com.example.test_outter

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.widget.Toast


class MyAIDLService : Service() {


    override fun onBind(intent: Intent): IBinder? {
        return object: MyTestAidl.Stub() {
            override fun funB(): Int { return 0 }

            override fun funA(data: String?) {}
        }
    }
}