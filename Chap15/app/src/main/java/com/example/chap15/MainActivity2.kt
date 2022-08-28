package com.example.chap15

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import java.sql.Connection



class MainActivity2 : AppCompatActivity() {


    lateinit var messenger: Messenger



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val intent = Intent(this, MyService2::class.java)
        intent.setPackage("com.example.test_outter")
        bindService(intent, connection, Context.BIND_AUTO_CREATE)


        // 내부 앱에 있는 메신저를 서비스에 전달하는 방법
        val msg = Message()
        msg.what = 10
        msg.obj = "hello"
        messenger.send(msg)


        //외부 앱의 데이터를 전달할 때 방법 위와 같은 방법을 쓰면 오류가 발생
        val bundle = Bundle()
        bundle.putString("data1", "hello")
        bundle.putInt("data2", 10)

        val msg2 = Message()
        msg2.what = 10
        msg2.obj = bundle
        messenger.send(msg2)

        unbindService(connection)
    }


    val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("Not yet implemented")
        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            messenger = Messenger(p1)
        }
    }
}