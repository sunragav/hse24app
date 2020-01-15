package com.sunragav.hse24app.views

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.sunragav.android_core.animations.animate
import com.sunragav.hse24app.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToNavCatalog()
            val navController = findNavController(R.id.fragment_nav_host)
            btnCart.setOnClickListener {
                it.animate {

                }
            }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.productsFragment, R.id.pdpFragment -> {
                        btnCart.show()
                        if (tvCartCount.text.isNotBlank()) tvCartCount.visibility = View.VISIBLE
                    }
                    else -> {
                        btnCart.hide()
                        tvCartCount.visibility = View.GONE
                    }
                }
            }
            navController.navigate(action)
        }, 1000)
    }
}
