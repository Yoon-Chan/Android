package com.example.chap17

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.chap17.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        //액티비티 데이터 저장
//        val sharedPref = getPreferences(Context.MODE_PRIVATE)
//
//        //앱 전체의 데이터 저장
//        val sharedPref2 = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//
//        sharedPref.edit().run {
//            putString("data1", "hello")
//            putInt("data2", 10)
//            commit()
//        }
//
//        val data1 = sharedPref.getString("data1", "world")
//        val data2 = sharedPref.getInt("data2", 10)


    }
}

//res/xml 파일의 settings xml 파일 코드 적용 클래스
class MySettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}


class MySettingFragment2 : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_two, rootKey)
    }
}