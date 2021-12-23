package com.mdfirst.dailyimage.ui

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.*
import coil.api.load
import com.google.android.material.textfield.*
import com.mdfirst.R
import com.mdfirst.dailyimage.domain.DailyImage

class DailyImageFragment : Fragment() {

    private val viewModel by viewModels<DailyImageViewModel>()

    private lateinit var dailyImageView: ImageView
    private lateinit var wikitextInputLayout: TextInputLayout
    private lateinit var wikitextEditText: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_daily_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dailyImageView = view.findViewById(R.id.image_view_daily_image)
        wikitextInputLayout = view.findViewById(R.id.input_layout_wiki)
        wikitextEditText = view.findViewById(R.id.input_edit_text_wiki)

        viewModel.getImageData().observe(viewLifecycleOwner, { dailyImage -> renderData(dailyImage) })
        viewModel.sendServerRequest()

//        wikitextEditText.setOnClickListener {
//            val isNightTheme = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
//            when (isNightTheme) {
//                Configuration.UI_MODE_NIGHT_YES ->
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                Configuration.UI_MODE_NIGHT_NO ->
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            }
//        }


        wikitextEditText.setOnClickListener {
            val newTheme = when (currentTheme) {
                R.style.Theme_MDFirst -> R.style.Theme_MDSecond
                R.style.Theme_MDSecond -> R.style.Theme_MDFirst
                else -> throw IllegalStateException("wrong theme")
            }

            currentTheme = newTheme

            requireActivity().recreate()
        }

        //wikitextInputLayout.error = "MyError"

//        wikitextInputLayout.setEndIconOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW)
//            val url = "https://en.wikipedia.org/wiki/${wikitextEditText.text.toString()}"
//            val uri = Uri.parse(url)
//            intent.data = uri
//
//            startActivity(intent)
//        }

        //setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
    }

    private fun renderData(
        dailyImage: DailyImage,
    ) {
        when (dailyImage) {
            is DailyImage.Success -> {
                val serverResponseData = dailyImage.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    // show error - empty link
                } else {
                    dailyImageView.load(url) {
                        lifecycle(this@DailyImageFragment)
                        error(R.drawable.ic_image_error)
                        placeholder(R.drawable.ic_empty_image)
                    }
                }
            }
            is DailyImage.Loading -> {
                // show error
            }
            is DailyImage.Error -> {
                // show error
            }
        }
    }

    companion object {

        // just for example don't do this
        var currentTheme = R.style.Theme_MDFirst
    }
}