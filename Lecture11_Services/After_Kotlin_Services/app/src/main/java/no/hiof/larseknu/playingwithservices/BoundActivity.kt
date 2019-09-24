package no.hiof.larseknu.playingwithservices

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bound.*
import no.hiof.larseknu.playingwithservices.service.MyBoundService

class BoundActivity : AppCompatActivity() {

    private var isBound = false
    private var myBoundService: MyBoundService? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            val myLocalBinder = iBinder as MyBoundService.MyLocalBinder

            myBoundService = myLocalBinder.service
            isBound = true
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound)
        supportActionBar?.title = "BoundActivity"
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MyBoundService::class.java)
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }

    fun getLocation(view: View) {
        if (isBound) {
            val location = myBoundService?.getCurrentLocation()
            if (location != null)
                locationTextView.text = "Lon: " + location!!.getLongitude() + "\nLat: " + location!!.getLatitude()
        }
    }
}