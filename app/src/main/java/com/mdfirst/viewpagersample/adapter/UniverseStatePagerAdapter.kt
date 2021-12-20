package com.mdfirst.viewpagersample.adapter

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mdfirst.viewpagersample.UniversePageFragment

class UniverseStatePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var items = listOf<UniversePageType>()

    override fun createFragment(position: Int): Fragment {
        val type = items[position]
        return UniversePageFragment.newInstance(type)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}