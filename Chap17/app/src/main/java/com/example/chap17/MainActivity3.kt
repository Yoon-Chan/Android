package com.example.chap17

import android.content.ContentUris
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.chap17.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        //공용 저장소에 접근
//        val projection = arrayOf(
//            MediaStore.Images.Media._ID,
//            MediaStore.Images.Media.DISPLAY_NAME
//        )
//
//        val cursor = contentResolver.query(
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//            projection,
//            null, null, null
//        )
//
//        cursor?.let {
//            while (cursor.moveToNext()){
//                Log.d("chan", "_id : ${cursor.getLong(0)}, name : ${cursor.getString(1)}")
//
//
//                //이미지 파일의 Uri값 가져오기
//                val contentUri : Uri = ContentUris.withAppendedId(
//                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                    cursor?.getLong(0)
//                )
//            }
//        }

//        val resolver = applicationContext.contentResolver
//        resolver.openInputStream(contentUri).use {
//            stream -> val option = BitmapFactory.Options()
//            option.inSampleSize = 10
//            val bitmap = BitmapFactory.decodeStream(stream, null, option)
//            binding.resultImageView.setImageBitmap(bitmap)
//        }
    }
}