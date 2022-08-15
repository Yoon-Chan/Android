package com.example.chap13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.chap13.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //키보드 올리고 내리기
        val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.showInputButton.setOnClickListener {
            binding.editView.requestFocus() // 뷰에 포커스 강제
            manager.showSoftInput(binding.editView, InputMethodManager.SHOW_IMPLICIT)
        }

        binding.hideInputButton.setOnClickListener {
            manager.hideSoftInputFromWindow(currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}