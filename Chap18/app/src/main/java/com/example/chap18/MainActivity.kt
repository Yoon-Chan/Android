package com.example.chap18

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.ServiceState
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import android.util.Log
import com.example.chap18.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //네트워크 접속 가능 여부
    private fun isNetworkAvailable() : Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.d("chan" , "wifi available")
                    true
                }
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.d("chan", "cellular available")
                    true
                }
                else -> false
            }
        }else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TelephonyCallback 사용 - 권장 방법
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            telephonyManager.registerTelephonyCallback(
                mainExecutor,
                object : TelephonyCallback(), TelephonyCallback.CallStateListener{
                    override fun onCallStateChanged(p0: Int) {
                        when(p0){
                            TelephonyManager.CALL_STATE_IDLE -> {
                                Log.d("chan", "IDLE")
                            }
                            TelephonyManager.CALL_STATE_OFFHOOK -> {
                                Log.d("chan", "OFFHOOK")
                            }
                            TelephonyManager.CALL_STATE_RINGING -> {
                                Log.d("chan", "RINGING")
                            }
                        }
                    }
                })
        }

        //네트워크 국가, 사업자, 전화번호 얻기
        val countryIso = telephonyManager.networkCountryIso
        val operatorName = telephonyManager.networkOperatorName
        val phoneNumber = telephonyManager.line1Number


        //네트워크 타입 지정
        val networkReq : NetworkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()






        //PhoneStateListener 사용 - 권장 방법은 아님
//        val phoneStateListener = object : PhoneStateListener() {
//            override fun onServiceStateChanged(serviceState: ServiceState?) {
//                super.onServiceStateChanged(serviceState)
//
//                when(serviceState?.state){
//                    ServiceState.STATE_EMERGENCY_ONLY -> Log.d("chan", "EMERGENCY_ONLY...")
//                    ServiceState.STATE_OUT_OF_SERVICE -> Log.d("chan", "OUT_OF_SERVICE...")
//                    ServiceState.STATE_POWER_OFF -> Log.d("chan", "POWER_OFF....")
//                    ServiceState.STATE_IN_SERVICE -> Log.d("chan", "IN_SERVICE....")
//                }
//            }
//
//            override fun onCallStateChanged(state: Int, phoneNumber: String?) {
//                super.onCallStateChanged(state, phoneNumber)
//
//                when(state){
//                    TelephonyManager.CALL_STATE_IDLE -> Log.d("chan", "IDLE...")
//                    TelephonyManager.CALL_STATE_RINGING -> Log.d("chan", "RINGING...")
//                    TelephonyManager.CALL_STATE_OFFHOOK -> Log.d("chan", "OFFHOOK...")
//                }
//            }
//        }
//
//        val manager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        manager.listen(phoneStateListener, PhoneStateListener.LISTEN_SERVICE_STATE or PhoneStateListener.LISTEN_CALL_STATE)
//
//
//        manager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE)
    }


}