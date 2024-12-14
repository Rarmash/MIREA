package com.rarmash.prac2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.rarmash.prac2.R

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        val buttonNext: Button = view.findViewById(R.id.btn_next)

        // Ручное управление
        buttonNext.setOnClickListener {
            val fragment2 = SecondFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment2)
                .addToBackStack(null)
                .commit()
        }

        // Навигация с помощью API
//        buttonNext.setOnClickListener {
//            findNavController().navigate(R.id.action_fragment1_to_fragment2)
//        }
        return view
    }
}