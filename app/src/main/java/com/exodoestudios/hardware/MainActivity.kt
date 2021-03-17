package com.exodoestudios.hardware

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAcelerometer = findViewById<Button>(R.id.buttonAcelerometer)
        val buttonGiroscope = findViewById<Button>(R.id.buttonGiroscope)
        val buttonStepCounter = findViewById<Button>(R.id.buttonStepCounter)
        val buttonCamera = findViewById<Button>(R.id.buttonCamera)
        val buttonMap = findViewById<Button>(R.id.buttonMap)
        val buttonProximity = findViewById<Button>(R.id.buttonProximity)
        val buttonGestures = findViewById<Button>(R.id.buttonGestures)
        val buttonLevel = findViewById<Button>(R.id.buttonLevel)
        val buttonDice = findViewById<Button>(R.id.buttonDice)


        buttonAcelerometer.setOnClickListener {
            startActivity(Intent(this, AcelerometerActivity::class.java))
        }

        buttonGiroscope.setOnClickListener {
            startActivity(Intent(this, GiroscopeActivity::class.java))
        }

        buttonStepCounter.setOnClickListener {
            startActivity(Intent(this, StepCounterActivity::class.java))
        }

        buttonCamera.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        buttonMap.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }

        buttonProximity.setOnClickListener {
            startActivity(Intent(this, ProximityActivity::class.java))
        }

        buttonGestures.setOnClickListener {
            startActivity(Intent(this, GestureActivity::class.java))
        }

        buttonLevel.setOnClickListener {
            startActivity(Intent(this, LevelActivity::class.java))
        }

        buttonDice.setOnClickListener {
            startActivity(Intent(this, DiceActivity::class.java))
        }

        /*val REQUEST_PERMISSION = 10

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_PERMISSION)
        }*/

        val REQUEST_PERMISSIONS = 11
        val PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (!hasPersmission(this, *PERMISSIONS))
        {
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSIONS)
        }

    }

    fun hasPersmission(context: Context, vararg permission: String): Boolean = permission.all{
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

}