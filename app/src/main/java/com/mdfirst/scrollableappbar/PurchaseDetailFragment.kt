package com.mdfirst.scrollableappbar

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import coil.api.load
import com.mdfirst.R

class PurchaseDetailFragment : Fragment() {

    private lateinit var expandedToolbarBackgroundImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_purchase_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expandedToolbarBackgroundImageView = view.findViewById(R.id.image_view_expanded_toolbar_background)
        val url = "https://taurus-m.ru/upload/iblock/16a/fk2dflirfrb8bn6tanuj5f1cjhd38knj.png"
        expandedToolbarBackgroundImageView.load(url)
    }
}