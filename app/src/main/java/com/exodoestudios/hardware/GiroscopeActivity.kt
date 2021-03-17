package com.exodoestudios.hardware

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class GiroscopeActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giroscope)

        val buttonBack = findViewById<Button>(R.id.buttonBack)

        buttonBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    }

    override fun onResume() {
        super.onResume()

        if (sensor != null)
        {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        else
        {
            Toast.makeText(this, "Sensor no disponible en el dispositivo", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0 != null) {
            if (p0.sensor.type == Sensor.TYPE_GYROSCOPE)
            {
                val textViewX = findViewById<TextView>(R.id.textViewX)
                val textViewY = findViewById<TextView>(R.id.textViewY)
                val textViewZ = findViewById<TextView>(R.id.textViewZ)

                textViewX.text = "X: " + p0.values[0].toString()
                textViewY.text = "Y: " + p0.values[1].toString()
                textViewZ.text = "Z: " + p0.values[2].toString()

            }
        }
    }

}