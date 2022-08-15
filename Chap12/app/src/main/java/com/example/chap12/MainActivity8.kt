package com.example.chap12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chap12.databinding.ActivityMain8Binding

class MainActivity8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMain8Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.extendFlotingActionbutton.setOnClickListener {
            when(binding.extendFlotingActionbutton.isExtended) {
                true -> binding.extendFlotingActionbutton.shrink()
                false -> binding.extendFlotingActionbutton.extend()
            }
        }
    }
}