package com.rarmash.prac10

import com.rarmash.prac10.ui.theme.Prac10Theme
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Prac10Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val imageUrlState = remember { mutableStateOf("") }
                    val bitmapState = remember { mutableStateOf<Bitmap?>(null) }
                    val isLoadingState = remember { mutableStateOf(false) }
                    val messageState = remember { mutableStateOf<String?>(null) }

                    ImageDownloaderScreen(
                        imageUrlState = imageUrlState,
                        bitmapState = bitmapState,
                        isLoadingState = isLoadingState,
                        messageState = messageState,
                        onDownloadClick = {
                            val imageUrl = imageUrlState.value
                            if (imageUrl.isNotBlank()) {
                                downloadAndSaveImage(
                                    imageUrl = imageUrl,
                                    context = this@MainActivity,
                                    bitmapState = bitmapState,
                                    isLoadingState = isLoadingState,
                                    messageState = messageState
                                )
                            } else {
                                messageState.value = "Введите корректный URL!"
                            }
                        }
                    )
                }
            }
        }
    }

    private fun downloadAndSaveImage(
        imageUrl: String,
        context: ComponentActivity,
        bitmapState: MutableState<Bitmap?>,
        isLoadingState: MutableState<Boolean>,
        messageState: MutableState<String?>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            isLoadingState.value = true
            val bitmapDeferred = downloadImage(imageUrl)
            val bitmap = bitmapDeferred.await()
            isLoadingState.value = false

            if (bitmap != null) {
                bitmapState.value = bitmap
                saveImageToDisk(bitmap, context, messageState)
            } else {
                messageState.value = "Ошибка загрузки изображения"
            }
        }
    }

    private fun downloadImage(imageUrl: String): Deferred<Bitmap?> {
        return CoroutineScope(Dispatchers.IO).async {
            try {
                val url = URL(imageUrl)
                val connection = url.openConnection()
                connection.doInput = true
                connection.connect()
                val input = connection.getInputStream()
                BitmapFactory.decodeStream(input)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun saveImageToDisk(
        bitmap: Bitmap,
        context: ComponentActivity,
        messageState: MutableState<String?>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val file = File(
                    context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    "downloaded_image.jpg"
                )
                FileOutputStream(file).use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream.flush()
                }
                withContext(Dispatchers.Main) {
                    messageState.value = "Изображение сохранено: ${file.path}"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    messageState.value = "Ошибка сохранения изображения"
                }
            }
        }
    }
}

@Composable
fun ImageDownloaderScreen(
    imageUrlState: MutableState<String>,
    bitmapState: MutableState<Bitmap?>,
    isLoadingState: MutableState<Boolean>,
    messageState: MutableState<String?>,
    onDownloadClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = imageUrlState.value,
            onValueChange = { imageUrlState.value = it },
            label = { Text("Введите URL изображения") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onDownloadClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Загрузить изображение")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (isLoadingState.value) {
            CircularProgressIndicator()
        } else {
            bitmapState.value?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Загруженное изображение",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        messageState.value?.let {
            Text(text = it)
        }
    }
}