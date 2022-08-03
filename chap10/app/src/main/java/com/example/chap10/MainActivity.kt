package com.example.chap10

import android.app.*
import android.content.DialogInterface
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.chap10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



//        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        val builder : NotificationCompat.Builder
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val channelId = "one -channel"
//            val channelName = "My Channel One"
//            val channel = NotificationChannel(
//                channelId,
//                channelName,
//                NotificationManager.IMPORTANCE_HIGH
//            )
//
//
//            //채널에 다양한 정보 설정
//            channel.description = "My Channel One Description"
//            channel.setShowBadge(true)
//            val uri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//            val audioAttributes = AudioAttributes.Builder()
//                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                .setUsage(AudioAttributes.USAGE_ALARM)
//                .build()
//
//            channel.setSound(uri, audioAttributes)
//            channel.enableLights(true)
//            channel.lightColor = Color.RED
//            channel.enableVibration(true)
//            channel.vibrationPattern = longArrayOf(100, 200, 100, 200)
//
//
//            //채널을 NotificationManager에 등록
//            manager.createNotificationChannel(channel)
//
//            //채널을 이용해 빌더 생성
//            builder = NotificationCompat.Builder(this, channelId)
//        }else {
//            builder = NotificationCompat.Builder(this)
//        }


//     커스텀 다이얼로그
//        val dialogBinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
//
//
//
//        AlertDialog.Builder(this).run {
//            setTitle("Input")
//            setView(dialogBinding.root)
//            setPositiveButton("닫기", null)
//            show()
//        }






// 아이템 사용 예제
//        val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
//        // 아이템 선택
//        AlertDialog.Builder(this).run {
//            setTitle("items test")
//            setIcon(android.R.drawable.ic_dialog_info)
//            setItems(items, object : DialogInterface.OnClickListener{
//                override fun onClick(p0: DialogInterface?, p1: Int) {
//                    Log.d("chan", "선택한 과일 : ${items[p1]}")
//                }
//            })
//            setCancelable(false)
//            setPositiveButton("닫기",null)
//            show()
//        }.setCanceledOnTouchOutside(false)

        //멀티 초이스 아이템
//        AlertDialog.Builder(this).run {
//            setTitle("items test")
//            setIcon(android.R.drawable.ic_dialog_info)
//            setMultiChoiceItems(items, booleanArrayOf(true, false, true, false), object :DialogInterface.OnMultiChoiceClickListener{
//                override fun onClick(p0: DialogInterface?, p1: Int, p2: Boolean) {
//                    Log.d("chan", "${items[p1]} 이 ${ if(p2) "선택되었습니다." else "선택 해제되었습니다."}")
//                }
//            })
//            setPositiveButton("닫기",null)
//            show()
//        }


//        AlertDialog.Builder(this).run {
//            setTitle("items test")
//            setIcon(android.R.drawable.ic_dialog_info)
//            setSingleChoiceItems(items, 1, object  : DialogInterface.OnClickListener{
//                override fun onClick(p0: DialogInterface?, p1: Int) {
//                    Log.d("chan", "${items[p1]}이 선택되었습니다.")
//                }
//            })
//            setPositiveButton("닫기",null)
//            show()
//        }



//        val eventHandler = object : DialogInterface.OnClickListener{
//            override fun onClick(p0: DialogInterface?, p1: Int) {
//                TODO("Not yet implemented")
//                if(p1 == DialogInterface.BUTTON_POSITIVE){
//                    Log.d("chan", "positive button click")
//                }
//                else if(p1 == DialogInterface.BUTTON_NEGATIVE){
//                    Log.d("chan", "negative button click")
//                }
//            }
//
//        }
//
//        AlertDialog.Builder(this).run {
//            setTitle("test dialog")
//            setIcon(android.R.drawable.ic_dialog_info)
//            setMessage("정말 종료하시겠습니까?")
//            setPositiveButton("OK", null)
//            setNegativeButton("Cancel",null)
//            setNeutralButton("More", null)
//            setPositiveButton("Yes",eventHandler)
//            setNegativeButton("No", eventHandler)
//            show()
//        }

//        DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
//            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
//                Log.d("chan", "year : $p1, month : ${p2+1}, dayOfMonth : $p3 ")
//            }
//        }, 2020, 8, 21).show()
//
//
//        TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener{
//            override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
//                Log.d("chan", "time : $p1, minute : $p2")
//            }
//        }, 15, 0, true).show()
    }







}