package com.example.chap09

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowMetrics
import com.example.chap09.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
            binding.textView.text =
                "width : ${windowMetrics.bounds.width()},height : ${windowMetrics.bounds.height()}"
        } else{
            val display = windowManager.defaultDisplay
            val displayMetrics = DisplayMetrics()
            display?.getRealMetrics(displayMetrics)
            binding.textView.text = "width : ${displayMetrics.widthPixels}, height : ${displayMetrics.heightPixels}"

        }

    }
//        }else{
//            val display = windowManager.defaultDisplay
//            val displayMetrics = DisplayMetrics()
//            display?.getRealMetrics(displayMetrics)
//            binding.textView?.text = "width : ${displayMetrics.widthPixels}, height : ${displayMetrics.heightPixels}"
//        }
//    }
}