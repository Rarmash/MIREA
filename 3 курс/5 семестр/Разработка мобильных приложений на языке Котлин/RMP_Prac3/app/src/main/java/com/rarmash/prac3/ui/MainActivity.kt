package com.rarmash.prac3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.rarmash.prac3.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (savedInstanceState == null) {
//            val fragment1: FirstFragment = FirstFragment()
//            val fragmentManager: FragmentManager = supportFragmentManager
//            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.fragment_container, fragment1)
//            fragmentTransaction.commit()
//        }
    }
}