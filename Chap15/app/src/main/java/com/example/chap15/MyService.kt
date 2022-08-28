package com.example.chap15

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder




class MyService : Service() {

    class Mybinder : Binder(){
        fun funA(Arg : Int){}
        fun funB(Arg : Int) : Int {
            return Arg * Arg
        }
    }


    override fun onBind(intent: Intent): IBinder {
        return Mybinder()
    }
}