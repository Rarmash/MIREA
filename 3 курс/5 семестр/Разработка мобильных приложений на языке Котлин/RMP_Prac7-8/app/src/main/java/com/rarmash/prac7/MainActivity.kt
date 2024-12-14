package com.rarmash.prac7

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rarmash.prac7.NetworkUtilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    val network = NetworkUtilities()
    lateinit var editTextURL: EditText
    lateinit var buttonDownload: Button
    lateinit var imageView: ImageView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextURL = findViewById(R.id.editTextUrl)
        buttonDownload = findViewById(R.id.buttonDownload)
        imageView = findViewById(R.id.imageView)

        buttonDownload.setOnClickListener {
            val imageURL = editTextURL.text.toString()
            downloadAndSaveImage(imageURL, this)
        }
    }

    private fun downloadAndSaveImage(imageUrl: String, context: Context) {
        CoroutineScope(Dispatchers.Main).launch {

            val bitmapDeferred = network.downloadImage(imageUrl)
            val bitmap = bitmapDeferred.await()
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap)
                saveImageToDisk(bitmap, context)
            } else {
                Toast.makeText(context, "Ошибка загрузки изображения", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun saveImageToDisk(bitmap: Bitmap?, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val file = File(
                    context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    "downloaded_image.jpg"
                )
                FileOutputStream(file).use { outputStream ->
                    if (bitmap != null) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    }
                    outputStream.flush()
                }
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Изображение сохранено: ${file.path}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Ошибка сохранения изображения", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}


