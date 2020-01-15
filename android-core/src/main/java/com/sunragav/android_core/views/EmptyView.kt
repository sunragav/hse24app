package com.sunragav.android_core.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.sunragav.android_core.R

class EmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    private val tvTitle by lazy { findViewById<AppCompatTextView>(R.id.tvEmptyTitle) }
    private val tvDescription by lazy { findViewById<AppCompatTextView>(R.id.tvEmptyDescription) }

    init {
        LayoutInflater.from(this.context).inflate(R.layout.view_empty, this, true)
        if (attrs != null) {
            context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.EmptyView,
                0, 0
            ).apply {
                try {
                    title = getString(R.styleable.EmptyView_title) ?: ""
                    description = getString(R.styleable.EmptyView_description) ?: ""
                } finally {
                    recycle()
                }
            }
        }
    }

    var title: String = ""
        set(value) {
            field = value
            tvTitle.text = value
        }
        get() = tvTitle.text.toString()

    var description: String = ""
        set(value) {
            field = value
            tvDescription.text = value
        }
        get() = tvDescription.text.toString()
}