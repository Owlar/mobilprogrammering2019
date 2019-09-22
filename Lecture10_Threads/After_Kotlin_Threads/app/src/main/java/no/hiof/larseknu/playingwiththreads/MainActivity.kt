package no.hiof.larseknu.playingwiththreads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val worker = Worker(this)

        val location = worker.retrieveLocation()
        val address = worker.reverseGeocode(location)

        Log.i("PlayingActivity", address)


    }
}
