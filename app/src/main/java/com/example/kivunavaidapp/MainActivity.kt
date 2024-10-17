package com.example.kivunavaidapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.data.kml.KmlLayer
import java.io.InputStream
import java.util.zip.ZipInputStream
import android.util.Log


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map


        // Center the map on a default location (e.g., Nairobi, Kenya)
        val defaultLocation = LatLng(-2.3931144667548616, 28.913709917196964)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10f))

        // Load and parse the KMZ file
        loadKmzFromAssets()
    }

    private fun loadKmzFromAssets() {
        try {
            // Open KMZ file from assets
            val kmzStream = assets.open("Direct_route_Rubavu-Rusizi100mbuffer.kmz")
            val zipInputStream = ZipInputStream(kmzStream)

            // Loop through the KMZ file to find the KML file
            var entry = zipInputStream.nextEntry
            while (entry != null) {
                if (entry.name.endsWith(".kml")) {
                    // Parse KML and display on map
                    val kmlLayer = KmlLayer(googleMap, zipInputStream, applicationContext)
                    kmlLayer.addLayerToMap()
                    break
                }
                entry = zipInputStream.nextEntry
            }
            zipInputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

