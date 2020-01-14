package com.sunragav.hse24app.views

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.sunragav.hse24app.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToNavCatalog()
            findNavController(R.id.fragment_nav_host).navigate(action)
        }, 1000)
    }
}
