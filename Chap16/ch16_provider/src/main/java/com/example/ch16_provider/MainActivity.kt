package com.example.ch16_provider

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.ch16_provider.databinding.ActivityMainBinding
import java.lang.Exception
import com.google.android.material.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight : Int) : Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds =true //옵션만 설정하고자 true로 지정
        try {
            var inputStream = contentResolver.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e : Exception){
            e.printStackTrace()
        }

        val (height : Int, width : Int ) = options.run { outHeight to outWidth }
        var inSamplSize = 1
        //inSampleSize 비율 계산
        if(height > reqHeight || width > reqWidth) {
            val halfHeight : Int = height/2
            val halfWidth : Int = width/2
            while (halfHeight / inSamplSize >= reqHeight && halfWidth/ inSamplSize >= reqWidth){
                inSamplSize *= 2
            }
        }

        return inSamplSize
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        lateinit var filePath: String

        setContentView(binding.root)

        //갤러리 요청 런처
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            try {
                //inSampleSize 비율, 계산 , 지정
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelOffset(R.dimen.design_fab_image_size),
                    resources.getDimensionPixelOffset(R.dimen.design_fab_image_size)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                //이미지 로딩
                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null
                bitmap?.let {
                    binding.userImageView.setImageBitmap(bitmap)
                } ?: let {
                    Log.d("chan","bitmap null")
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }

        binding.galleryButton.setOnClickListener {
            //갤러리 앱
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }


        //카메라 요청 런처
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            //카메라 앱
            val calRatio =calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelOffset(R.dimen.design_fab_image_size),
                resources.getDimensionPixelOffset(R.dimen.design_fab_image_size)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        binding.cameraButton.setOnClickListener {
            //카메라 앱
            //파일 준비
            val timeStamp : String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir : File? =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath
            val photoURI : Uri = FileProvider.getUriForFile(
                this,
                "com.example.ch16_provider.fileprovider", file
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraFileLauncher.launch(intent)
        }
    }



}