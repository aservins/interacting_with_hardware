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

class StepCounterActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor

    var countedSteps = 0
    var counter = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_counter)

        val buttonBack = findViewById<Button>(R.id.buttonBack)
        val buttonReset = findViewById<Button>(R.id.buttonReset)

        buttonBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        buttonReset.setOnClickListener {
            countedSteps = 0;
            counter = 0;
            val textViewCountedSteps = findViewById<TextView>(R.id.textViewCountedSteps)
            textViewCountedSteps.text = "Pasos Contados: 0"
        }

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    }

    override fun onResume() {
        super.onResume()

        if (sensor != null)
        {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST)
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
            if (p0.sensor.type == Sensor.TYPE_STEP_COUNTER)
            {
                if (countedSteps < 1)
                {
                    countedSteps = p0.values[0].toInt()
                }

                counter = p0.values[0].toInt() - countedSteps
                val textViewCountedSteps = findViewById<TextView>(R.id.textViewCountedSteps)
                textViewCountedSteps.text = "Pasos Contados: " + counter.toString()
            }
        }
    }
}