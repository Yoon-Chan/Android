package com.example.chap13

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.chap13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("data1", "hello")
        intent.putExtra("data2", 10)

        //ActivityResultLauncher 사용방법
        val requestLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            val resulData = it.data?.getStringExtra("result")
            binding.mainResultView.text = "result : $resulData"
        }

        requestLauncher.launch(intent)

        //startActivityForResult 사용방법
//        startActivityForResult(intent,10)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


    //startActivityForResult 사용방법
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 10 && resultCode == Activity.RESULT_OK){
//            val result = data?.getStringExtra("resultData")
//        }
//    }
}