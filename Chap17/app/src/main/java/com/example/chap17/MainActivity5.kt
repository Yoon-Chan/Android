package com.example.chap17

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.preference.*
import com.example.chap17.databinding.ActivityMain5Binding

class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val id = sharedPreference.getString("id", "")


    }

}


class EditSetting : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        if(p1 == "id"){
            Log.i("chan", "newValue : "+p0?.getString("id", ""))
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.editsetting, rootKey)

        val idPreference : EditTextPreference? = findPreference("id")
        val colorPreference : ListPreference? = findPreference("color")


//        idPreference?.summaryProvider =
//            EditTextPreference.SimpleSummaryProvider.getInstance()

        //설정값대로 summary 내용 추가
        idPreference?.summaryProvider =
            Preference.SummaryProvider<EditTextPreference> {
                preference ->
                val text = preference.text
                if(TextUtils.isEmpty(text)) {
                    "설정이 되지 않았스니라."
                } else {
                    "설정된 ID 값은 : $text 입니다."
                }
            }

        //이벤트 핸들러 지정
        idPreference?.setOnPreferenceClickListener {
            preference ->
            Log.d("chan", "preference key : ${preference.key}")
            true
        }

        //설정 변경 순간 감지 방법 1
        idPreference?.setOnPreferenceChangeListener { preference, newValue ->
            Log.d("chan", "preference key : ${preference.key}, newValue : ${newValue}")
            true
        }


        colorPreference?.summaryProvider =
            ListPreference.SimpleSummaryProvider.getInstance()


//        idPreference?.isVisible = true
//
//        idPreference?.summary = "code summary"
//        idPreference?.title = "code title"
    }
}
