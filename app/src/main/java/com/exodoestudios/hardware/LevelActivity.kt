package com.exodoestudios.hardware

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class LevelActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor

    var imageViewLevel: ImageView? = null
    var bitmap: Bitmap? = null
    var canvas: Canvas? = null

    var canvasWidth = 0
    var canvasHeight = 0
    var halfCanvasWidth = 0
    var halfCanvasHeight = 0

    var bubbleWidth = 150
    var bubbleHeight = 150
    var halfBubbleWidth = bubbleWidth / 2
    var halfBubbleHeight = bubbleHeight / 2
    var bubbleAreaFactor = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        imageViewLevel = findViewById(R.id.imageViewLevel)

        val buttonBack = findViewById<Button>(R.id.buttonBack)

        buttonBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

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
            if (p0.sensor.type == Sensor.TYPE_ACCELEROMETER)
            {
                var x = p0.values[0]
                var y = p0.values[1]

                x = (Math.pow(x.toDouble(), 2.0) * 2).toFloat()
                y = (Math.pow(y.toDouble(), 2.0) * 2).toFloat()

                if (p0.values[0] < 0)
                {
                    x *= -1 // x = x * -1
                }

                if (p0.values[1] > 0)
                {
                    y *= -1 // x = x * -1
                }

                levelBubble(imageViewLevel!!, x, y)

            }
        }
    }

    fun levelBubble(iv: ImageView, x: Float, y: Float)
    {
        try
        {
            canvasWidth = iv.width
            canvasHeight = iv.height
            halfCanvasWidth = canvasWidth / 2
            halfCanvasHeight = canvasHeight / 2

            bitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888)
            canvas = Canvas(bitmap!!)

            imageViewLevel?.setImageBitmap(bitmap)

            val bubble = RectF(
                halfCanvasWidth - halfBubbleWidth + x,
                halfCanvasHeight - halfBubbleHeight + y,
                halfCanvasWidth + halfBubbleWidth + x,
                halfCanvasHeight + halfBubbleHeight + y
            )

            val bubbleArea = RectF(
                (halfCanvasWidth - halfBubbleWidth * bubbleAreaFactor).toFloat(),
                (halfCanvasHeight - halfBubbleHeight * bubbleAreaFactor).toFloat(),
                (halfCanvasWidth + halfBubbleWidth * bubbleAreaFactor).toFloat(),
                (halfCanvasHeight + halfBubbleHeight * bubbleAreaFactor).toFloat()
            )

            val paint = Paint()
            paint.color = Color.argb(255, 0,0,255)
            paint.strokeWidth = 3f

            canvas?.drawLine(0f,0f, canvasWidth.toFloat(), 0f, paint)
            canvas?.drawLine(canvasWidth - 1f,0f, canvasWidth -1f, canvasHeight -1f, paint)
            canvas?.drawLine(0f, canvasHeight -1f, canvasWidth -1f, canvasHeight -1f, paint)
            canvas?.drawLine(0f,0f, 0f, canvasHeight -1f, paint)

            canvas?.drawLine(0f,halfCanvasHeight -1f, canvasWidth -1f, halfCanvasHeight -1f, paint)
            canvas?.drawLine(halfCanvasWidth -1f,0f, halfCanvasWidth -1f, canvasHeight -1f, paint)

            paint.color = Color.argb(255,0, 255,0)
            canvas?.drawOval(bubbleArea, paint)

            paint.color = Color.argb(128, 150, 50, 150)
            canvas?.drawOval(bubble, paint)
        }
        catch (e: Exception)
        {

        }
    }

}