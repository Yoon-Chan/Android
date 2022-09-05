package com.example.chap19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chap19.databinding.ActivityMain2Binding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MainActivity2 : AppCompatActivity(), OnMapReadyCallback {
    lateinit var binding : ActivityMain2Binding
    var googleMap : GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)

        setContentView(binding.root)
        (supportFragmentManager.findFragmentById(R.id.mapView2) as SupportMapFragment?)!!.getMapAsync(this)


        val latLng = LatLng(37.566610, 126.978403)
        val position = CameraPosition.builder()
            .target(latLng)
            .zoom(16f)
            .build()
        googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(position))

//        //마커 표시
//        val markerOptions = MarkerOptions()
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
//        markerOptions.position(latLng)
//        markerOptions.title("서울 시청")
//        markerOptions.snippet("Tel:01-120")
//
//        //마커 표시하기
//        googleMap?.addMarker(markerOptions)
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap = p0
    }
}