package com.mdfirst.viewpagersample

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.*
import com.mdfirst.R
import com.mdfirst.viewpagersample.adapter.*

class UniverseFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_universe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.view_pager_universe)
        val adapter = UniverseStatePagerAdapter(this)

        adapter.items = UniversePageType.values().toList()
        viewPager.adapter = adapter

        viewPager.setPageTransformer(ZoomOutPageTransformer())

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                Log.d("VIEW PAGER", "onPageScrolled, position $position, positionOffset $positionOffset")
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("VIEW PAGER", "onPageSelected, positon $position")
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING or SCROLL_STATE_SETTLING.
                Log.d("VIEW PAGER", "onPageScrollStateChanged, state $state")
            }
        })

        tabLayout = view.findViewById(R.id.tab_layout_universe)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val type = adapter.items[position]
            tab.text = type.name

            tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_people)
        }.attach()

    }
}