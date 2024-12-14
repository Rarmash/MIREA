package com.rarmash.prac4

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.rarmash.prac4.databinding.FragmentCameraBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraFragment : Fragment() {
    private lateinit var binding: FragmentCameraBinding
    private lateinit var cameraExecutor: ExecutorService // Камера в отдельном потоке
    private lateinit var viewFinder: PreviewView // Изображение

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.all { it.value }) {
                startCamera()
            } else {
                requireActivity().finish()
            }
        }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        binding.btnPht.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentCamera_to_fragmentPhotos)
        }

        cameraExecutor = Executors.newSingleThreadExecutor()
        viewFinder = binding.previewView
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissionLauncher.launch(REQUIRED_PERMISSIONS)
        }

        binding.btnTake.setOnClickListener {
            saveDateTimeToFile()
        }
        return binding.root
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext()) // Подвязка камеры к ЖЦ активности

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(viewFinder.surfaceProvider)
            }

            // Выбор камеры
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all { permission ->
        ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun saveDateTimeToFile() {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
        val currentDateTime = dateFormat.format(Date())

        val photosDir =
            File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photos")
        if (!photosDir.exists()) {
            photosDir.mkdirs()
        }

        val dateFile = File(photosDir, "photos.txt")
        try {
            FileOutputStream(dateFile, true).use { outputStream ->
                outputStream.write("$currentDateTime\n".toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}