package com.example.estudoandroid

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.checkSelfPermission
import androidx.core.app.ActivityCompat.requestPermissions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), LocationListener {

    //inicializar de forma tardia
    //pa getSystemService só pode ser usado após onCreate
    private val locationManager by lazy {
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private var ultimaLocalizacao: Location? = null

    private var mapa: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciarLocalizacao()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {

        when(requestCode) {
            LOCATION_PERMISSION -> if(grantResults[0] == PERMISSION_GRANTED)
                iniciarLocalizacao()
        }
    }

    private fun iniciarLocalizacao() {

        if(checkSelfPermission(this, ACCESS_FINE_LOCATION) != PERMISSION_GRANTED
                && checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {


            requestPermissions(this,
                    arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                    LOCATION_PERMISSION)

            return
        }

//        val locationProvider = LocationManager.NETWORK_PROVIDER
        val locationProvider = LocationManager.GPS_PROVIDER

        locationManager.requestLocationUpdates(
                locationProvider,
                500,
                0.5F,
                this)


        val bestLocation = locationManager.getLastKnownLocation(locationProvider)

        ultimaLocalizacao = bestLocation

        iniciarMaps()
    }

    private fun iniciarMaps() {
        val mapFrament = supportFragmentManager.findFragmentById(R.id.fragMap) as SupportMapFragment
        mapFrament.getMapAsync { map ->
            mapa = map
            ultimaLocalizacao?.let {
                val latLng = LatLng(it.latitude, it.longitude)

                map.addMarker(MarkerOptions().position(latLng).title("Start Position"))

                //troca  a posicao da camera
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14F))

            }
        }
    }

    private fun atualizarPosicao() {

        ultimaLocalizacao?.let {
            val latLng = LatLng(it.latitude, it.longitude)

            //troca  a posicao da camera
            mapa?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14F))

        }

    }

    //traz localizacao atualizada
    override fun onLocationChanged(location: Location?) {
        ultimaLocalizacao = location

        atualizarPosicao()
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    companion object {
        private const val LOCATION_PERMISSION = 1
    }
}
