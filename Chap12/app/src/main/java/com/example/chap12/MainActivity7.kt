package com.example.chap12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chap12.databinding.ActivityMain7Binding

class MainActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMain7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainDrawerView.setNavigationItemSelectedListener {
            Log.d("chan", "navigation Item Click ... ${it.title}")
            true
        }

    }
}