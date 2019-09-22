package no.hiof.larseknu.playingwiththreads

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Environment
import android.util.Log

import org.json.JSONException
import org.json.JSONObject

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Worker(private val context: Context) {



    fun retrieveLocation() : Location {
        var lastLocation: Location? = null

        if (useGpsToGetLocation) {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        }

        if (lastLocation == null) {
            lastLocation = createLocationManually()
        }

        addDelay()
        return lastLocation!!
    }


    fun reverseGeocode(location: Location) : String {
        val geocoder = Geocoder(context)

        val addressList = geocoder.getFromLocation(location.latitude, location.longitude, 5)

        addDelay()

        return if (addressList.isNullOrEmpty()) {
            addressList[0].toString()
        } else {
            "B.R.A. veien 4, 1757 Halden"
        }
    }

    private fun addDelay() {
        Thread.sleep(2000)
    }

    /**
     * Creates a location manually for HIØ to use for testing
     *
     * @return The location of HIØ
     */
    private fun createLocationManually(): Location? {
        val lastLocation = Location("Hiof")
        val now = Date()
        lastLocation.time = now.time
        lastLocation.latitude = 59.128229
        lastLocation.longitude = 11.352860

        return lastLocation
    }

    companion object {
        var useGpsToGetLocation = true
    }
}
