package com.sunragav.android_core.animations

import android.animation.Animator
import android.view.View

fun View.animate(callback: () -> Unit) {
    animate().scaleX(0.7F).scaleY(0.7F).setDuration(100)
        .withEndAction {
            animate().scaleX(1F).scaleY(1F).setListener(null)
        }.setListener(object :
            Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                callback.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
}