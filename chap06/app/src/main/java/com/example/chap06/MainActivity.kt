package com.example.chap06

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        //바인딩 객체 획등
//        val binding = ActivityMainBinding.inflate(layoutInflater)

        //액티비티 화면 출력
        setContentView(R.layout.activity_main)

//        //뷰 객체 이용
//        binding.visibleBtn.setOnClickListener {
//            binding.targetView.visibility = View.VISIBLE
//        }
//        binding.invisibleBtn.setOnClickListener {
//            binding.targetView.visibility = View.INVISIBLE
//        }
//
//        val txtView1: TextView = findViewById(R.id.text1)
//        val button1 = findViewById<Button>(R.id.button1)
//
//        button1.setOnClickListener {
//            it.visibility = View.INVISIBLE
//        }
//
//        //이름 문자열 출력 TextView 생성
//        val name = TextView(this).apply {
//            typeface = Typeface.DEFAULT_BOLD
//            text = "Lake Louise"
//        }
//
//        //이미지 출력 ImageView 생성
//        val image = ImageView(this).also {
//            it.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lake_1))
//        }
//
//        //주소 문자열 출력 TextView 생성
//        val address = TextView(this).apply {
//            typeface = Typeface.DEFAULT_BOLD
//            text = "Lake Louise, AB, 캐나다"
//        }
//
//        val layout = LinearLayout(this).apply {
//            orientation = LinearLayout.VERTICAL
//            gravity = Gravity.CENTER
//            // LinearLayout 객체에 TextView, ImageView, TextView 객체 추가
//            addView(name, WRAP_CONTENT, WRAP_CONTENT)
//            addView(image, WRAP_CONTENT, WRAP_CONTENT)
//            addView(address, WRAP_CONTENT, WRAP_CONTENT)
//        }
//
//        setContentView(layout)
    }
}