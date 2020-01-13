package com.sunragav.android_core.extensions

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.sunragav.android_core.R

fun RecyclerView.runLayoutAnimation() {
    layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.list_fall_down_animation)
    adapter?.notifyDataSetChanged()
    scheduleLayoutAnimation()
}