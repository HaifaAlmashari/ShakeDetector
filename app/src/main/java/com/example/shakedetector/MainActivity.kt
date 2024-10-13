package com.example.shakedetector

import android.content.Context
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.widget.Toast
import com.google.androidgamesdk.gametextinput.Listener
import com.squareup.seismic.ShakeDetector

class MainActivity : AppCompatActivity() {

    val sensorManager
    val shakeDetector

    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    if (vibrator.hasVibrator())

        // start vibration

//    else {
//        // device has no vibration feature
//    }

    // vibrate 100 milliseconds and sleep 1000 milliseconds
    // vibrate 200 milliseconds and sleep 2000 milliseconds

    val vibrationPattern= longArrayOf(0, 100, 1000, 200, 2000)

// Start the vibration
    vibrator.vibrate(vibrationPattern, -1) // does not repeat
    vibrator.vibrate(vibrationPattern, 0) // repeats forever

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        shakeDetector = ShakeDetector(ShakeDetector.Listener {
            Toast.makeText(this, "Shake detected!", Toast.LENGTH_SHORT)
                .show()
        })
    }

    override fun onResume() {
        super.onResume()
        shakeDetector.start(sensorManager)
    }

    override fun onPause() {
        super.onPause()
        shakeDetector.stop()
    }

    }