package com.exodoestudios.hardware

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat

class GestureActivity : AppCompatActivity() {

    private var gestureDetector: GestureDetectorCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture)

        val buttonBack = findViewById<Button>(R.id.buttonBack)

        buttonBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        gestureDetector = GestureDetectorCompat(this, GestureListener())

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector?.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    inner class GestureListener: GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
    {
        override fun onShowPress(p0: MotionEvent?) {
            //Toast.makeText(this@GestureActivity, "onShowPress", Toast.LENGTH_LONG).show()
        }

        override fun onSingleTapUp(p0: MotionEvent?): Boolean {
            //Toast.makeText(this@GestureActivity, "onSingleTapUp", Toast.LENGTH_LONG).show()
            return true
        }

        override fun onDown(p0: MotionEvent?): Boolean {
            //Toast.makeText(this@GestureActivity, "onDown", Toast.LENGTH_LONG).show()
            return true
        }

        override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
            //Toast.makeText(this@GestureActivity, "onFling: " + p0?.x.toString() + ", " + p0?.y.toString(), Toast.LENGTH_LONG).show()
            return true
        }

        override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
            //Toast.makeText(this@GestureActivity, "onScroll", Toast.LENGTH_LONG).show()
            return true
        }

        override fun onLongPress(p0: MotionEvent?) {
            //Toast.makeText(this@GestureActivity, "onLongPress", Toast.LENGTH_LONG).show()
        }

        override fun onDoubleTap(p0: MotionEvent?): Boolean {
            //Toast.makeText(this@GestureActivity, "onDoubleTap", Toast.LENGTH_LONG).show()
            return true
        }

        override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
            //Toast.makeText(this@GestureActivity, "onDoubleTapEvent", Toast.LENGTH_LONG).show()
            return true
        }

        override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
            Toast.makeText(this@GestureActivity, "onSingleTapConfirmed", Toast.LENGTH_LONG).show()
            return true
        }

    }



}