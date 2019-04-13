package com.example.kirim_in
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_maps.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
    override fun onMarkerClick(p0: Marker?) = false
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private var isFABOpen = false

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


    }
    private fun showFABMenu() {
        isFABOpen = true
        fab_normal.animate().translationY(-resources.getDimension(R.dimen.standard_60))
        fab_stelite.animate().translationY(-resources.getDimension(R.dimen.standard_120))
        fab_hybrid.animate().translationY(-resources.getDimension(R.dimen.standard_180))
        fab_terrain.animate().translationY(-resources.getDimension(R.dimen.standard_240))
    }

    private fun closeFABMenu() {
        isFABOpen = false
        fab_normal.animate().translationY(0f)
        fab_stelite.animate().translationY(0f)
        fab_hybrid.animate().translationY(0f)
        fab_terrain.animate().translationY(0f)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = false
        mMap.setOnMarkerClickListener(this)
        setUpMap()
        fab.setOnClickListener {
            Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
            if (!isFABOpen){
                showFABMenu()
            }else{
                closeFABMenu()
            }
        }

    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ), LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentPos = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentPos)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPos, 18.0f))

            }
        }
        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

        val normal = findViewById<FloatingActionButton>(R.id.fab_normal)
        val satellite = findViewById<FloatingActionButton>(R.id.fab_stelite)
        val hybrid = findViewById<FloatingActionButton>(R.id.fab_hybrid)
        val terain = findViewById<FloatingActionButton>(R.id.fab_terrain)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        normal.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            Toast.makeText(this, "Menu Normal", Toast.LENGTH_SHORT).show()

        }
        satellite.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            Toast.makeText(this, "Menu Satellite", Toast.LENGTH_SHORT).show()

        }
        hybrid.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            Toast.makeText(this, "Menu Hybrid", Toast.LENGTH_SHORT).show()

        }
        terain.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            Toast.makeText(this, "Menu Terrain", Toast.LENGTH_SHORT).show()

        }
        fab.setOnClickListener {
            Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
        }

    }

    private fun placeMarkerOnMap(loc : LatLng){
        val markerOptions = MarkerOptions().position(loc)
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(
            BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher)
        ))
        mMap.addMarker(markerOptions)
    }
}
