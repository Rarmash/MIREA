package com.rarmash.prac12

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.rarmash.prac12.ui.theme.Prac12Theme
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Prac12Theme {
                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Prac12Theme {
        ImageDownloadApp()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var currentScreen by remember { mutableStateOf("ImageDownloader") }
    val screens = listOf("ImageDownloader", "Info", "Settings")


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(16.dp))
                screens.forEach { screen ->
                    NavigationDrawerItem(
                        label = { Text(screen) },
                        selected = currentScreen == screen,
                        onClick = {
                            currentScreen = screen
                            scope.launch { drawerState.close() }
                        },
                        icon = { Icon(Icons.Default.Favorite, contentDescription = null) }
                    )
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = currentScreen) },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) {
                                        drawerState.open()
                                    }
                                }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = null)
                            }
                        }
                    )
                },
                bottomBar = {
                    BottomAppBar {
                        screens.forEach { screen ->
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                                label = { Text(screen) },
                                selected = currentScreen == screen,
                                onClick = { currentScreen = screen }
                            )
                        }
                    }
                },
                content = { contentPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                            .padding(16.dp)
                    ) {
                        when (currentScreen) {
                            "ImageDownloader" -> ImageDownloadApp()
                            "Info" -> InfoScreen()
                            "Settings" -> SettingsScreen()
                        }
                    }
                }
            )
        },
    )
}

@Composable
fun ImageDownloadApp() {
    var url by remember { mutableStateOf("") }
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val lifecycleOwner = LocalLifecycleOwner.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("Enter Image URL") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (url.isNotEmpty()) {
                    val workManager = WorkManager.getInstance(context)
                    val inputData = workDataOf("imageUrl" to url)

                    val downloadWorkRequest = OneTimeWorkRequestBuilder<ImageDownloadWorker>()
                        .setInputData(inputData)
                        .build()
                    workManager.enqueue(downloadWorkRequest)


                    workManager.getWorkInfoByIdLiveData(downloadWorkRequest.id)
                        .observe(lifecycleOwner) { workInfo ->
                            if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                                val imagePath = workInfo.outputData.getString("imagePath")
                                if (imagePath != null) {
                                    val imageBitmap = BitmapFactory.decodeFile(imagePath)
                                    bitmap = imageBitmap
                                    Toast.makeText(
                                        context,
                                        "Image downloaded to: $imagePath",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Download Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.size(400.dp)
            )
        }
    }
}

class ImageDownloadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        val imageUrl = inputData.getString("imageUrl") ?: return Result.failure()
        return try {
            val bitmap = downloadImage(imageUrl)
            bitmap?.let {
                val file = saveImageToDisk(it, applicationContext)
                val outputData = workDataOf("imagePath" to file.absolutePath)
                Result.success(outputData)
            } ?: Result.failure()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}

@Composable
fun InfoScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Information Screen", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Settings Screen", style = MaterialTheme.typography.titleLarge)
    }
}

private fun downloadImage(url: String): Bitmap? {
    return try {
        val inputStream: InputStream = URL(url).openStream()
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

private fun saveImageToDisk(bitmap: Bitmap, context: Context): File {
    val directory =
        File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "downloadedImages")
    if (!directory.exists()) {
        directory.mkdirs()
    }
    val file = File(directory, "downloaded_image.png")
    val outputStream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    outputStream.flush()
    outputStream.close()
    return file
}