package com.exodoestudios.hardware

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class DiceActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor

    lateinit var textViewNum1: TextView
    lateinit var textViewNum2: TextView

    lateinit var switchNumDice: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)

        textViewNum1 = findViewById(R.id.textViewNum1)
        textViewNum2 = findViewById(R.id.textViewNum2)
        switchNumDice = findViewById(R.id.switchNumDice)

        val buttonBack = findViewById<Button>(R.id.buttonBack)

        buttonBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        switchNumDice.setOnCheckedChangeListener{
            _, isChecked ->
            if (isChecked)
            {
                textViewNum1.text = "1"
                textViewNum2.text = "1"
                textViewNum2.visibility = View.VISIBLE
            }
            else
            {
                textViewNum1.text = "1"
                textViewNum2.text = "1"
                textViewNum2.visibility = View.INVISIBLE
            }
        }

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        while (!sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE).isWakeUpSensor)
        {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        }



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
                var x = p0.values[0]
                var y = p0.values[1]
                var z = p0.values[2]

                x = (Math.pow(x.toDouble(), 2.0)).toFloat()
                y = (Math.pow(y.toDouble(), 2.0)).toFloat()
                z = (Math.pow(z.toDouble(), 2.0)).toFloat()

                if (x > 30 && y > 30 && z > 30)
                {
                    var n1 = Random.nextInt(6) + 1
                    var n2 = Random.nextInt(6) + 1

                    textViewNum1.text = n1.toString()
                    textViewNum2.text = n2.toString()
                }
            }
        }
    }
}