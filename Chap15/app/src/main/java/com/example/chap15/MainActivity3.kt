package com.example.chap15

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log

class MainActivity3 : AppCompatActivity() {
    //외부 앱의 서비스 실행을 위한 변수
    lateinit var aidlService : MyTestAidl



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //외부 앱의 서비스 실행
        val intent = Intent("ACTION_SERVICE_AIDL")
        intent.setPackage("com.example.test_outter")
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
        aidlService.funA("hello")
    }

    val connection : ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.d("chan", "onServiceDisconnected")
        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            aidlService=MyTestAidl.Stub.asInterface(p1)

        }
    }

}