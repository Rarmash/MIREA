package com.rarmash.prac4

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rarmash.prac4.databinding.FragmentPhotosBinding
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class PhotosFragment : Fragment() {
    private lateinit var binding: FragmentPhotosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)

        val data = readDateTimeFromFile()
        val adapter = MyAdapter(data)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    private fun readDateTimeFromFile(): List<String> {
        val photosDir =
            File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photos")
        val dateFile = File(photosDir, "photos.txt")
        val data = mutableListOf<String>()

        try {
            FileInputStream(dateFile).use { inputStream ->
                InputStreamReader(inputStream).use { reader ->
                    reader.forEachLine { line ->
                        data.add(line)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return data
    }
}