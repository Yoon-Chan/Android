package com.example.chap17

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.example.chap17.databinding.ActivityMain2Binding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        //파일 객체 생성 후 데이터 쓰기
//        val file = File(filesDir, "test.txt")
//        val writeStream : OutputStreamWriter = file.writer()
//        writeStream.write("hello world")
//        writeStream.flush()
//
//
//        val readStream : BufferedReader = file.reader().buffered()
//        readStream.forEachLine {
//            Log.d("chan", "$it")
//        }
//
//
//        //Context 객체의 함수 사용
//        openFileOutput("test.txt", Context.MODE_PRIVATE).use {
//            it.write("hello world!!".toByteArray())
//        }
//        openFileInput("test.txt").bufferedReader().forEachLine {
//            Log.d("chan", "$it")
//        }
//
//
//        //외장 메모리 사용 가능 여부 판단
//        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
//            Log.d("chan", "ExternalStorageState MOUNTED")
//        } else {
//            Log.d("chan", "ExternalStorageState UNMOUNTED")
//        }
//
//        //앱별 저장소에 접근
//        val file2 : File? = getExternalFilesDir(null)
//        Log.d("chan", "${file2?.absolutePath}")
//
//
//        //앱별 저장소에 파일 쓰기와 읽기
//        val file3 : File = File(getExternalFilesDir(null), "test.txt")
//        val writeStream3 : OutputStreamWriter = file3.writer()
//        writeStream3.write("hello world")
//        writeStream3.flush()
//
//        //파일 읽기
//        val readStream3 : BufferedReader = file3.reader().buffered()
//        readStream3.forEachLine {
//            Log.d("chan", "$it")
//        }




    }
}