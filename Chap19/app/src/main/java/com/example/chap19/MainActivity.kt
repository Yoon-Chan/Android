package com.example.chap19

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.chap19.databinding.ActivityMainBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 위치 매니저 사용
        val manager = getSystemService(LOCATION_SERVICE) as LocationManager

        //모든 위치 제공자 알아보기
        var result = "All Providers : "
        val providers = manager.allProviders
        for(provider in providers){
            result += "$provider, "
        }
        Log.d("chan", result)   // ALl Providers : passive, gps, network,


        //지금 사용할 수 있는 위치 제공자 알아보기
        var result2 = "Enabled Providers : "
        val enabledProviders = manager.getProviders(true)
        for (provider in enabledProviders){
            result2 += "$provider, "
        }
        Log.d("chan", result2)  //Enabled Providers : passive, gps, network,



        // 위치 한 번만 가져오기
        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
        ) === PackageManager.PERMISSION_GRANTED) {
            val location : Location ?=
                manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            location?.let {
                val latitude = location.latitude
                val longitude = location.longitude
                val accuracy = location.accuracy
                val time = location.time

                Log.d("chan", "$latitude, $longitude, $accuracy, $time")
            }

        }


        //위치 계속 가져오기
        val listener : LocationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {
                Log.d("chan", "${p0.latitude},  ${p0.longitude}, ${p0.accuracy}")
            }

            override fun onProviderDisabled(provider: String) {
                super.onProviderDisabled(provider)
            }

            override fun onProviderEnabled(provider: String) {
                super.onProviderEnabled(provider)
            }
        }

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10_000L, 10f, listener)

        manager.removeUpdates(listener)



        //GoogleApiClient 초기화
        val connectionCallback = object : GoogleApiClient.ConnectionCallbacks{
            override fun onConnected(p0: Bundle?) {
                //위치 제공자를 사용할 수 있을 때
                //위치 획득

            }

            override fun onConnectionSuspended(p0: Int) {
                //위치 제공자를 사용할 수 없을 때
            }
        }

        val onConnectionFailedCallback = object : GoogleApiClient.OnConnectionFailedListener {
            override fun onConnectionFailed(p0: ConnectionResult) {
                //사용할 수 있는 위치 제공자가 없을 때
            }
        }

        val apiCLient : GoogleApiClient = GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .addConnectionCallbacks(connectionCallback)
            .addOnConnectionFailedListener(onConnectionFailedCallback)
            .build()


        //FusedLocationProviderClient 초기화
        val providerClent : FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        //위치 제공자 요청
        apiCLient.connect()
    }
}