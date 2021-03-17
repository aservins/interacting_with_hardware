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

class ProximityActivity : AppCompatActivity(), SensorEventListener{

    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximity)

        val buttonBack = findViewById<Button>(R.id.buttonBack)

        buttonBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

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
            if (p0.sensor.type == Sensor.TYPE_PROXIMITY)
            {
                val textViewProximity = findViewById<TextView>(R.id.textViewProximity)

                if (p0.values[0] != 0f)
                {
                    textViewProximity.text = " -- ENCENDIDO -- "
                }
                else
                {
                    textViewProximity.text = " -- APAGADO -- "
                }
            }
        }
    }
}