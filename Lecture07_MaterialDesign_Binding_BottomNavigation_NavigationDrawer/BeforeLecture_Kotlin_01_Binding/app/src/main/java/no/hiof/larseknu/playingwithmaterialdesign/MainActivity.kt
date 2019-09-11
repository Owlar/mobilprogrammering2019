package no.hiof.larseknu.playingwithmaterialdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var countTextView : TextView
    private lateinit var countButton : Button

    private var counter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate Called")
        setContentView(R.layout.activity_main)

        countTextView = findViewById(R.id.countTextView)
        countButton = findViewById(R.id.countButton)

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("COUNTER_ID")

            countTextView.text = counter.toString()
        }

        countButton.setOnClickListener{ view ->
            counter++

            countTextView.setText(counter.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("COUNTER_ID", counter)
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy Called")
    }
}
