package com.rarmash.prac7

import org.junit.runners.JUnit4
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnit4::class)
class MainActivityModuleTest {
    @Test
    fun testImageIsLoaded() {
        val network = NetworkUtilities()

        val imageUrl = "https://i.imgur.com/VSaDVhp.jpeg"
        val bitmap = network.downloadImage(imageUrl)

        assertNotNull(bitmap)
    }
}