package com.sunragav.android_core.deeplinks

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import com.sunragav.android_core.R

class DeepLinks {
    companion object {
        const val CATALOG = "hse24://catalog"
        const val PRODUCTS = "hse24://products"
        const val PDP = "hse24://pdp"
        const val CART = "hse24://cart"
    }
}

fun createDefaultNavOptions(destination: Int) = NavOptions.Builder()
    .setLaunchSingleTop(false)
    .setPopUpTo(destination, false)
    .setEnterAnim(R.anim.nav_default_enter_anim)
    .setExitAnim(R.anim.nav_default_exit_anim)
    .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
    .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
    .build()

fun NavController.navigateUriWithDefaultOptions(
    uri: Uri,
    extras: FragmentNavigator.Extras? = null
) {
    this.navigate(uri, createDefaultNavOptions(this.currentDestination?.id ?: -1), extras)
}