// pointing to current location
//package com.example.kivunavaidapp
//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationCallback
//import com.google.android.gms.location.LocationRequest
//import com.google.android.gms.location.LocationResult
//import com.google.android.gms.location.LocationServices
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions
//import com.google.maps.android.data.kml.KmlLayer
//import java.io.InputStream
//import java.util.zip.ZipInputStream
//import android.util.Log
//
//class MainActivity : AppCompatActivity(), OnMapReadyCallback {
//
//    private lateinit var googleMap: GoogleMap
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    private lateinit var locationCallback: LocationCallback
//
//    companion object {
//        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Set up the map fragment
//        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
//
//        // Initialize fused location client for real-time location updates
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//
//        // Define the location callback to receive real-time location updates
//        locationCallback = object : LocationCallback() {
//            override fun onLocationResult(locationResult: LocationResult) {
//                super.onLocationResult(locationResult)
//                val location = locationResult.lastLocation
//                if (location != null) {
//                    val currentLocation = LatLng(location.latitude, location.longitude)
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
//                    googleMap.addMarker(MarkerOptions().position(currentLocation).title("You are here"))
//                }
//            }
//        }
//    }
//
//    override fun onMapReady(map: GoogleMap) {
//        googleMap = map
//
//        // Check and request location permissions if not granted
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                LOCATION_PERMISSION_REQUEST_CODE
//            )
//            return
//        }
//
//        // Enable my location layer and set up location updates
//        googleMap.isMyLocationEnabled = true
//        getCurrentLocationAndMoveCamera()
//        startLocationUpdates()
//
//        // Load the KMZ file from the assets folder
//        loadKmzFromAssets()
//    }
//
//    // Handle permission request results
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getCurrentLocationAndMoveCamera()
//                startLocationUpdates()
//            }
//        }
//    }
//
//    // Get the user's current location and move the camera to it
//    private fun getCurrentLocationAndMoveCamera() {
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//                if (location != null) {
////                    val currentLocation = LatLng(-2.3931144667548616, 28.913709917196964)
//                    val currentLocation = LatLng(location.latitude, location.longitude)
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
//                    googleMap.addMarker(
//                        MarkerOptions().position(currentLocation).title("Current Location")
//                    )
//                }
//            }
//        }
//    }
//
//    // Start real-time location updates
//    private fun startLocationUpdates() {
//        val locationRequest = LocationRequest.create().apply {
//            interval = 5000 // 5 seconds
//            fastestInterval = 2000 // 2 seconds
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        }
//
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
//        }
//    }
//
//    // Load and display the KMZ file from assets
//    private fun loadKmzFromAssets() {
//        try {
//            val kmzStream = assets.open("Direct_route_Rubavu-Rusizi100mbuffer.kmz")
//            val zipInputStream = ZipInputStream(kmzStream)
//
//            // Loop through the KMZ file to find the KML file
//            var entry = zipInputStream.nextEntry
//            while (entry != null) {
//                if (entry.name.endsWith(".kml")) {
//                    val kmlLayer = KmlLayer(googleMap, zipInputStream, applicationContext)
//                    kmlLayer.addLayerToMap()
//                    break
//                }
//                entry = zipInputStream.nextEntry
//            }
//            zipInputStream.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    // Stop receiving location updates when the activity is paused
//    override fun onPause() {
//        super.onPause()
//        fusedLocationClient.removeLocationUpdates(locationCallback)
//    }
//}

// addjusting coorinate
//
//package com.example.kivunavaidapp
//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions
//import com.google.maps.android.data.kml.KmlLayer
//import java.util.zip.ZipInputStream
//
//class MainActivity : AppCompatActivity(), OnMapReadyCallback {
//
//    private lateinit var googleMap: GoogleMap
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
//    }
//
//    override fun onMapReady(map: GoogleMap) {
//        googleMap = map
//
//
//        // Manual coordinates (you can change these)
//        val manualCoordinates = LatLng(-1.7341285183742254, 29.255936528595097) // Example: Nairobi, Kenya
//
//        // Update the map location and add a marker
//        updateMapLocation(manualCoordinates)
//
//        // Load and parse the KMZ file
//        loadKmzFromAssets()
//    }
//
//    private fun updateMapLocation(location: LatLng) {
//        // Move the camera to the new location
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
//
//        // Add a marker at the location
//        googleMap.addMarker(MarkerOptions().position(location).title("My Location"))
//
//        // Enable the blue "my location" dot on the map (if location services are available)
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        googleMap.isMyLocationEnabled = true
//    }
//
//    private fun loadKmzFromAssets() {
//        try {
//            // Open KMZ file from assets
//            val kmzStream = assets.open("Direct_route_Rubavu-Rusizi100mbuffer.kmz")
//            val zipInputStream = ZipInputStream(kmzStream)
//
//            // Loop through the KMZ file to find the KML file
//            var entry = zipInputStream.nextEntry
//            while (entry != null) {
//                if (entry.name.endsWith(".kml")) {
//                    // Parse KML and display on map
//                    val kmlLayer = KmlLayer(googleMap, zipInputStream, applicationContext)
//                    kmlLayer.addLayerToMap()
//                    break
//                }
//                entry = zipInputStream.nextEntry
//            }
//            zipInputStream.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//}

// adding search field

package com.example.kivunavaidapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.data.kml.KmlLayer
import java.io.InputStream
import java.util.*
import java.util.zip.ZipInputStream

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var currentMarker: Marker? = null // For tracking the current marker
    private var isSearching = false // To check if a search is active

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the map fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Initialize fused location client for real-time location updates
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Initialize search functionality
        val searchView = findViewById<SearchView>(R.id.search_location)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchLocation(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        // Define the location callback to receive real-time location updates
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                // Only update location if the user is not actively searching
                if (!isSearching) {
                    val location = locationResult.lastLocation
                    if (location != null) {
                        val currentLocation = LatLng(location.latitude, location.longitude)
                        updateMapLocation(currentLocation, "You are here")
                    }
                }
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Check and request location permissions if not granted
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        // Enable my location layer and set up location updates
        googleMap.isMyLocationEnabled = true
        getCurrentLocationAndMoveCamera()
        startLocationUpdates()

        // Load the KMZ file from the assets folder
        loadKmzFromAssets()
    }

    // Handle permission request results
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocationAndMoveCamera()
                startLocationUpdates()
            }
        }
    }

    // Get the user's current location and move the camera to it
    private fun getCurrentLocationAndMoveCamera() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null && !isSearching) {
                    val currentLocation = LatLng(location.latitude, location.longitude)
                    updateMapLocation(currentLocation, "Current Location")
                }
            }
        }
    }

    // Start real-time location updates
    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = 5000 // 5 seconds
            fastestInterval = 2000 // 2 seconds
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
        }
    }

    // Update the map with a new marker and move the camera
    private fun updateMapLocation(latLng: LatLng, title: String) {
        currentMarker?.remove() // Remove previous marker
        currentMarker = googleMap.addMarker(MarkerOptions().position(latLng).title(title)) // Add new marker
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f)) // Move camera
    }

    // Load and display the KMZ file from assets
    private fun loadKmzFromAssets() {
        try {
            val kmzStream = assets.open("Direct_route_Rubavu-Rusizi100mbuffer.kmz")
            val zipInputStream = ZipInputStream(kmzStream)

            // Loop through the KMZ file to find the KML file
            var entry = zipInputStream.nextEntry
            while (entry != null) {
                if (entry.name.endsWith(".kml")) {
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

    // Stop receiving location updates when the activity is paused
    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    // Search location based on query (either by name or coordinates)
    private fun searchLocation(query: String) {
        isSearching = true // Mark that user is searching
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocationName(query, 1)

            // Check if addresses is not null and contains at least one result
            if (addresses != null && addresses.isNotEmpty()) {
                val address = addresses[0]
                val searchedLocation = LatLng(address.latitude, address.longitude)
                updateMapLocation(searchedLocation, query)
            } else {
                // Try to parse the query as latitude,longitude format
                val latLng = query.split(",").map { it.trim().toDoubleOrNull() }

                // Ensure both latitude and longitude are valid numbers
                if (latLng.size == 2 && latLng[0] != null && latLng[1] != null) {
                    val searchedLocation = LatLng(latLng[0]!!, latLng[1]!!)
                    updateMapLocation(searchedLocation, "Custom Location")
                } else {
                    // Handle invalid input
                    Log.e("searchLocation", "Invalid location format: $query")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
