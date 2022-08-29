package com.example.chap16


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.chap16.databinding.ActivityMainBinding
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
        val binding = ActivityMainBinding.inflate(layoutInflater)
        lateinit var filePath: String

        super.onCreate(savedInstanceState)
        setContentView(binding.root)





//        contentResolver.query(
//            Uri.parse("content://com.example.chap16"),
//            null, null, null, null)



        //주소록 앱 연동하기
        val requestContactsLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == RESULT_OK){
                Log.d("chan", "${it.data?.data}")

                val cursor = contentResolver.query(
                    it!!.data!!.data!!,
                    arrayOf<String>(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    ),
                    null,
                    null,
                    null
                )

                Log.d("chan", "cursor size .... ${cursor?.count}")
                if(cursor!!.moveToFirst()){
                    val name = cursor?.getString(0)
                    val phone = cursor?.getString(1)
                    binding.resultContack.text = "name : $name, phone: $phone"
                }
            }
        }


        //이미지 앱 연동
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            try {
                // inSampleSize 비율 계산, 지정
                val calRatio = calculateInSampleSize(it!!.data!!.data!!,
                resources.getDimensionPixelOffset(R.dimen.design_fab_image_size),
                resources.getDimensionPixelOffset(R.dimen.design_fab_image_size))

                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                //이미지 로딩
                var inputStream = contentResolver.openInputStream(it!!.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null
                bitmap?.let {
                    binding.galleryResult.setImageBitmap(bitmap)
                } ?: let {
                    Log.d("chan", "bitmap null")
                }

            }catch (e :Exception){
                e.printStackTrace()
            }
        }

        //카메라 사진 데이터 가져오기
        val requestCameraThumbnailLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            //val bitmap = it?.data?.extras?.get("data") as Bitmap
            val option = BitmapFactory.Options()
            option.inSampleSize = 10
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.cameraFileResult.setImageBitmap(bitmap)
            }
        }

        binding.button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)

        }




        //버튼이 눌렸을 떄 주소록 앱 연동
        binding.button.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
            requestContactsLauncher.launch(intent)
        }

        binding.button3.setOnClickListener {
            //파일 만들기
            val timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir : File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )

            filePath = file.absolutePath


            val photoURI : Uri = FileProvider.getUriForFile(this,
            "com.example.chap16.fileprovider", file)

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraThumbnailLauncher.launch(intent)


        }

        //지도 앱 연동하기
        binding.button4.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.5662952, 126.9779451"))
            startActivity(intent)
        }

        //전화 앱 연동하기
        binding.button5.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:02-120"))
            startActivity(intent)
        }


//        //주소록 앱 연동하기
//        val requestContactsLauncher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult())
//        {
//            if(it.resultCode == RESULT_OK){
//                Log.d("chan", "${it.data?.data}")
//
//                val cursor = contentResolver.query(
//                    it!!.data!!.data!!,
//                    arrayOf<String>(
//                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
//                        ContactsContract.CommonDataKinds.Phone.NUMBER
//                    ),
//                    null,
//                    null,
//                    null
//                )
//
//                Log.d("chan", "cursor size .... ${cursor?.count}")
//                if(cursor!!.moveToFirst()){
//                    val name = cursor?.getString(0)
//                    val phone = cursor?.getString(1)
//
//                }
//            }
//        }

//        val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
//        requestContactsLauncher.launch(intent)
    }
}