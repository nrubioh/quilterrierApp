package com.example.quilterrier

import android.Manifest
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.GoogleMap
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.SupportMapFragment
import com.example.quilterrier.R
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.location.Location
import com.example.quilterrier.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.Marker

// TODO añadir algun boton de prueba
class MapsActivity : FragmentActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    private var binding: ActivityMapsBinding? = null
    private var marcador: Marker? = null
    var lat = 0.0
    var lng = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        miUbicacion()
    }

    private fun agregarMarcador(lat: Double, lng: Double) {
        val coordenadas = LatLng(lat, lng)
        val miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16f)
        if (marcador != null) marcador!!.remove()
        marcador = mMap!!.addMarker(
            MarkerOptions().position(coordenadas).title("Mi posición actual")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
        )
        mMap!!.animateCamera(miUbicacion)
    }

    private fun actualizarUbicacion(location: Location?) {
        if (location != null) {
            lat = location.latitude
            lng = location.longitude
            agregarMarcador(lat, lng)
        }
    }

    var locListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            actualizarUbicacion(location)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    private fun miUbicacion() {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        actualizarUbicacion(location)
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0f, locListener)
    }
}