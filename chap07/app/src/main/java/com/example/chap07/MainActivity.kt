package com.example.chap07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

//        //뷰 바인딩
//        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)

//        binding.button.setOnClickListener {
//            binding.button.visibility = View.INVISIBLE
//            binding.imageView.visibility = View.VISIBLE
//        }
//
//        binding.imageView.setOnClickListener {
//            binding.button.visibility = View.VISIBLE
//            binding.imageView.visibility = View.INVISIBLE
//        }
    }
}