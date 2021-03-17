package com.exodoestudios.hardware

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity() {

    lateinit var imageFromCamera: String
    val REQUEST_IMAGE_CAPTURE = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val buttonBack = findViewById<Button>(R.id.buttonBack)
        val buttonPhoto = findViewById<Button>(R.id.buttonPhoto)

        buttonBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        buttonPhoto.setOnClickListener {
            val builder = StrictMode.VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            val imageName: String = "IMG_" + SimpleDateFormat ("yyyyMMdd_HHmmss").format(Date()) + ".JPG"
            val imagePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES) as File
            val imageFile = File(imagePath, imageName)

            imageFromCamera = imageFile.toString()
            val imageUri = Uri.fromFile(imageFile)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK)
        {
            val imageView = findViewById<ImageView>(R.id.imageView)
            imageView.setImageURI(Uri.parse(imageFromCamera))
        }

    }

}