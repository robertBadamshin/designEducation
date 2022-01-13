package com.mdfirst.textsamples.ui

import android.content.Context
import android.graphics.Color
import android.text.Spanned
import android.text.style.*
import android.view.View
import androidx.core.text.toSpannable
import androidx.lifecycle.*
import com.mdfirst.R
import com.mdfirst.core.metrics.dpToPx
import com.mdfirst.recyclerview.ui.model.*
import com.mdfirst.textsamples.ui.roundedbackgroundspan.RoundedBackgroundSpan
import java.util.*

private const val initialTextForSpan = "It is text for span. * for Image Span."

class TextSampleViewModel : ViewModel() {

    private val spannableTextLiveData = MutableLiveData<CharSequence>()
    private val messageTextLiveData = MutableLiveData<String>()

    fun getSpannableText(): LiveData<CharSequence> {
        return spannableTextLiveData
    }

    // нужно позаботиться о том, чтобы сообщение показалось только один раз, потому что при пересоздании фрагмента,
    // фрагмент может подписаться уже на существующую ViewModel, и ему придет старое сообщение и покажется еще раз.
    // Такое может произойти при перевороте экрана, при открытии / закрытии экрана у Falaxy Fold.
    // пример https://github.com/ArtNikita/Material_Design/pull/1/files , класс Event.kt
    fun getMessageText(): LiveData<String> {
        return messageTextLiveData
    }

    fun onBackgroundColorSpanClicked() {
        val spannable = initialTextForSpan.toSpannable()

//        val backgroundSpan = RoundedBackgroundSpan(
//            textColor = Color.BLACK,
//            backgroundColor = Color.RED,
//            padding = 4.dpToPx(),
//            cornerRadius = 4.dpToPx(),
//            marginStart = 2.dpToPx(),
//            marginEnd = 2.dpToPx(),
//        )

        spannable.setSpan(BackgroundColorSpan(Color.CYAN), 3, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannableTextLiveData.value = spannable
    }

    fun onClickableSpanClicked() {
        val clickableSpan = object : ClickableSpan() {

            override fun onClick(widget: View) {
                messageTextLiveData.value = "Span Clicked"
            }
        }

        val spannable = initialTextForSpan.toSpannable()
        spannable.setSpan(clickableSpan, 3, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannableTextLiveData.value = spannable
    }

    fun onImageSpanClicked(context: Context) {
        val imageSpan = ImageSpan(context, R.drawable.ic_image_span_sample)

        val spannable = initialTextForSpan.toSpannable()
        spannable.setSpan(imageSpan, 3, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannableTextLiveData.value = spannable
    }
}