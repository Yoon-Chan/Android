package com.example.chap17

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.chap17.databinding.ActivityMain4Binding
//분할 설정 화면을 보여주는 액티비티 코드
class MainActivity4 : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat,
        pref: Preference
    ): Boolean {
        //새로운 프래그먼트 인스턴스화
        val args = pref.extras
        val fragment = supportFragmentManager.fragmentFactory.instantiate(
            classLoader,
            pref.fragment.toString()
            )
        fragment.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.setting_content, fragment)
            .addToBackStack(null)
            .commit()
        return true
    }


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
        setPreferencesFromResource(R.xml.settings_three, rootKey)
    }
}

class ASettingFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.asetting, rootKey)
    }
}

class BSettingFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.bsetting, rootKey)
    }
}