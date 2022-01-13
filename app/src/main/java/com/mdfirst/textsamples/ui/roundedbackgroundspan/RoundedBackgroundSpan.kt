package com.mdfirst.textsamples.ui.roundedbackgroundspan

import android.graphics.*
import android.text.style.ReplacementSpan
import androidx.annotation.ColorInt
import com.mdfirst.core.metrics.dpToPx
import kotlin.math.roundToInt

class RoundedBackgroundSpan(
    @ColorInt
    private val textColor: Int,
    @ColorInt
    private val backgroundColor: Int,
    private val cornerRadius: Int,
    private val padding: Int,
    private val marginStart: Int,
    private val marginEnd: Int
) : ReplacementSpan() {

    private var height = 15.dpToPx()

    private var backgroundRect = RectF()

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        //paint.typeface = tex

        val textHeight = paint.descent() - paint.ascent()
        val textAndSpanHeightDelta = (height - textHeight) / 2

        val backgroundBottom = y + paint.descent() + textAndSpanHeightDelta
        val backgroundTop = backgroundBottom - height
        backgroundRect = RectF(
            x + marginStart,
            backgroundTop,
            x + marginStart + getRectWidth(paint, text, start, end),
            backgroundBottom
        )

        paint.color = backgroundColor
        canvas.drawRoundRect(backgroundRect, cornerRadius.toFloat(), cornerRadius.toFloat(), paint)

        // draw vertically centered text on background
        val textRect = RectF(backgroundRect)
        textRect.bottom = paint.descent() - paint.ascent()
        textRect.top += (backgroundRect.height() - textRect.bottom) / 2.0f - paint.ascent()

        paint.color = textColor
        canvas.drawText(
            text, start, end, x + marginStart + padding, textRect.top, paint
        )
    }

    override fun getSize(
        paint: Paint, text: CharSequence, start: Int, end: Int, fm: Paint.FontMetricsInt?
    ): Int {
        // If the span covers the whole text, and the height is not set,
        // draw(Canvas, CharSequence, int, int, float, int, int, int, Paint) will not be called for the span.
        // Setting FontMetricsInt params fixes this behaviour.
        if (fm != null) {
            val paintFontMetrics = paint.fontMetrics
            fm.ascent = paintFontMetrics.ascent.toInt()
            fm.bottom = paintFontMetrics.bottom.toInt()
            fm.descent = paintFontMetrics.descent.toInt()
            fm.leading = paintFontMetrics.leading.toInt()
            fm.top = paintFontMetrics.top.toInt()
        }

        return marginStart + getRectWidth(paint, text, start, end).roundToInt() + marginEnd
    }

    private fun getRectWidth(paint: Paint, text: CharSequence, start: Int, end: Int): Float {
        return 2 * padding + paint.measureText(text, start, end)
    }
}
