package com.example.chap18

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.chap18.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //파일 이미지 출력
        val requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){

            Glide.with(this)
                .load(it.data!!.data!!)
                .into(binding.resultView)
        }

        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        requestLauncher.launch(intent)

        //서버 이미지 출력
        val url = "https://google.com"
        Glide.with(this)
            .load(url)
            .into(binding.resultView)

        //크기 조절
        Glide.with(this)
            .load(R.drawable.ic_launcher_foreground)
            .override(200, 200)
            .into(binding.resultView)

        //로딩, 오류 이미지 출력
        Glide.with(this)
            .load(url)
            .override(200, 200)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.resultView)


        //이미지 데이터 사용하기
        Glide.with(this)
            .load(url)
            .into(object : CustomTarget<Drawable>(){
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    TODO("Not yet implemented")
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })

        //람다 함수 선언
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    TODO("Not yet implemented")
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })

        Glide.with(this)
            .load(R.drawable.ic_launcher_background)
            .into(binding.resultView)
    }
}