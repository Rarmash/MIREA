package com.rarmash.prac2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.rarmash.prac2.R


class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)

        // Ручное управление
        val buttonNext: Button = view.findViewById(R.id.btn_next)
        val buttonBack: Button = view.findViewById(R.id.btn_back)

        buttonNext.setOnClickListener {
            val fragment3 = ThirdFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit()
        }
        buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // API
//        buttonNext.setOnClickListener {
//            findNavController().navigate(R.id.action_fragment2_to_fragment3)
//        }
//
//        buttonBack.setOnClickListener {
//            findNavController().popBackStack()
//        }

        return view
    }
}