package com.example.chap08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import com.example.chap08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

//        binding.checkBox.setOnCheckedChangeListener { compoundButton, b ->
//            Log.d("chan", "체크박스 클릭")
//        }

        binding.button.setOnClickListener {
            Log.d("chna", "클릭 이벤트")
        }

        binding.button.setOnLongClickListener {
            Log.d("chan","롱클릭 이벤트")
            true
        }
    }


//    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
//        TODO("Not yet implemented")
//        Log.d("chan", "체크박스 클릭")
//    }
//
//}

//class MyEventHandler : CompoundButton.OnCheckedChangeListener{
//    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
//
//        Log.d("chan", "체크박스 클릭")
//    }

}