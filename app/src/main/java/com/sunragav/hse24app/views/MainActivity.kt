package com.sunragav.hse24app.views

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.sunragav.android_core.animations.animate
import com.sunragav.android_core.deeplinks.DeepLinks
import com.sunragav.android_core.deeplinks.navigateUriWithDefaultOptions
import com.sunragav.hse24app.R
import com.sunragav.presentation.CartViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        val cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        cartViewModel.cartCount.observe(this, Observer {
            if (it > 0 && btnCart.isVisible) {
                tvCartCount.text = it.toString()
                tvCartCount.visibility = View.VISIBLE
            } else {
                tvCartCount.text = "0"
                tvCartCount.visibility = View.GONE
            }
        })
        Handler().postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToNavCatalog()
            val navController = findNavController(R.id.fragment_nav_host)
            btnCart.setOnClickListener {
                it.animate {
                    navController
                        .navigateUriWithDefaultOptions(Uri.parse(DeepLinks.CART))
                }
            }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.productsFragment, R.id.pdpFragment -> {
                        btnCart.show()
                        if (tvCartCount.text.isNotBlank() && tvCartCount.text.toString().toInt() > 0) tvCartCount.visibility =
                            View.VISIBLE
                        else tvCartCount.visibility = View.GONE
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
