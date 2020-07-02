package com.example.desafioandroid.ui.shared.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import com.example.desafioandroid.R
import kotlinx.android.synthetic.main.description_value_text_view.view.*

/**
 * A component to use value and deescription without the creation of 2 textviews per item.
 */
class DescriptionValueTextView : ConstraintLayout {

    var description: String? = ""
        set(value) {
            field = value
            tvTitle.text = value
        }
    var value: String? = ""
        set(value) {
            field = value
            tvValue.text = value
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        setupView(attrs)
    }

    private fun setupView(attrs: AttributeSet? = null) {
        inflate(context, R.layout.description_value_text_view, this)

        attrs?.apply {
            val attributes =
                context.obtainStyledAttributes(this, R.styleable.DescriptionValueTextView)
            val typeface =
                attributes.getInt(R.styleable.DescriptionValueTextView_descriptionTextStyle, 0)
            typeface.takeIf {
                it > 0
            }?.run {
                when (this) {
                    1 -> tvTitle.typeface = Typeface.create("", Typeface.BOLD)
                    2 -> tvTitle.typeface = Typeface.create("", Typeface.ITALIC)
                }
            }

            attributes.getBoolean(R.styleable.DescriptionValueTextView_IsSubItemTitle, false)
                .takeIf {
                    it
                }?.run {
                tvTitle.setTextColor(resources.getColor(R.color.colorFontBlack))
            }

            tvTitle.text = attributes.getString(R.styleable.DescriptionValueTextView_description)
            tvValue.text = attributes.getString(R.styleable.DescriptionValueTextView_value)

            attributes.recycle()
        }
    }
}
