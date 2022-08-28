package com.example.chap15

import android.annotation.TargetApi
import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobService : JobService() {

    override fun onCreate() {
        super.onCreate()
        Log.d("chan", "MyJobService ..... onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("chan", "MyJobService ..... onDestroy()")
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.d("chan", "MyJobService......onStartJob()")
        return false
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d("chan","MyJobService......onStopJob()")
        return false
    }

}