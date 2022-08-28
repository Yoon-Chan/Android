package com.example.chap15

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        //ServiceConnection 인터페이스 구현
        val connection : ServiceConnection = object : ServiceConnection {
            override fun onServiceDisconnected(p0: ComponentName?) {
                TODO("Not yet implemented")
            }

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                //여기에서 서비스가 보낸 객체를 전달받아 사용한다.
                var servideBinder = p1 as MyService.Mybinder
                servideBinder.funA(10)
            }
        }

        //bindService를 이용해 서비스 실행
        val intent = Intent(this, MyService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)



        //bindService 함수 종료
        unbindService(connection)

        //startService() 실행 방법
//        val intent = Intent(this, MyService::class.java)
// //       intent.setPackage("com.example.test_outter")
//        startService(intent)
//
//        //서비스 종료 함수
//        stopService(intent)
    }
}