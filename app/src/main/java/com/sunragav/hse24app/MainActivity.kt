package com.sunragav.hse24app

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            findNavController(R.layout.activity_main).navigate(Uri.parse("hse24://catalog"))
            finish()
        }, 1000)
    }
}
