package com.mdfirst.textsamples.ui

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import com.mdfirst.R
import java.lang.IllegalArgumentException

class TextSampleFragment : Fragment() {

    private val viewModel by viewModels<TextSampleViewModel>()

    private lateinit var spannableSampleTextView: TextView
    private lateinit var radioGroupSpan: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_text_sample, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spannableSampleTextView = view.findViewById(R.id.text_view_spanned_text)
        radioGroupSpan = view.findViewById(R.id.radio_group_span)

        viewModel.getSpannableText().observe(viewLifecycleOwner) { text ->
            spannableSampleTextView.text = text
        }

        viewModel.getMessageText().observe(viewLifecycleOwner) { text ->
            Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
        }


        radioGroupSpan.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_button_background_color_span -> viewModel.onBackgroundColorSpanClicked()
                R.id.radio_button_clickable_span -> viewModel.onClickableSpanClicked()
                R.id.radio_button_image_span -> viewModel.onImageSpanClicked(requireContext())
                else -> throw IllegalArgumentException("unknown id")
            }
        }

        spannableSampleTextView.movementMethod = LinkMovementMethod.getInstance()

        val radioButton: RadioButton = view.findViewById(R.id.radio_button_clickable_span)
        radioButton.isChecked = true

        radioButton.setOnCheckedChangeListener { _, isChecked ->

        }
    }
}