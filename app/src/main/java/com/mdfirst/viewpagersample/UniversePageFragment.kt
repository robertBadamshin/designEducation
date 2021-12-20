package com.mdfirst.viewpagersample

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mdfirst.R
import com.mdfirst.viewpagersample.adapter.UniversePageType

private const val pageTypeKey = "pageTypeKey"

class UniversePageFragment : Fragment() {

    private val universePageType by lazy {
        requireArguments().getSerializable(pageTypeKey) as UniversePageType
    }
    private lateinit var titleTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_universe_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleTextView = view.findViewById(R.id.text_view_universe_page_title)

        titleTextView.text = universePageType.name
    }

    companion object {

        fun newInstance(universePageType: UniversePageType): UniversePageFragment {
            val bundle = Bundle()
            bundle.putSerializable(pageTypeKey, universePageType)

            val fragment = UniversePageFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}