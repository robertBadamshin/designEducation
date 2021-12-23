package com.mdfirst.viewpagersample

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.mdfirst.R
import com.mdfirst.viewpagersample.adapter.UniversePageType

class UniverseFragment : Fragment() {

    private lateinit var fragmentContainerView: View
    private lateinit var bottomNavigationView: BottomNavigationView

    private val navigationItemSelectedListener = NavigationBarView.OnItemSelectedListener { item ->
        //this logic will be move into view model
        val fragment = when (item.itemId) {
            R.id.bottom_view_earth -> UniversePageFragment.newInstance(UniversePageType.Earth)
            R.id.bottom_view_mars -> UniversePageFragment.newInstance(UniversePageType.Mars)
            R.id.bottom_view_weather -> UniversePageFragment.newInstance(UniversePageType.Weather)
            else -> throw IllegalArgumentException("Select unknown item")
        }

        openScreen(fragment)
        true
    }


    private val navigationItemReselectedListener = NavigationBarView.OnItemReselectedListener { item ->
        // you can scroll to top for one of fragment, for example
    }

    private val backPressedReturnMainCallback = object : OnBackPressedCallback(true) {

        override fun handleOnBackPressed() {
            // call backPressedReturnMainCallback.enabled = false after setting earth
            // don't forget to set enabled = true if not earth
            openScreen(UniversePageFragment.newInstance(UniversePageType.Earth))
            bottomNavigationView.selectedItemId = R.id.bottom_view_earth
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(backPressedReturnMainCallback)

        openScreen(UniversePageFragment.newInstance(UniversePageType.Earth))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_universe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentContainerView = view.findViewById(R.id.view_fragment_container)

        bottomNavigationView = view.findViewById(R.id.navigation_view_main)
        bottomNavigationView.setOnItemSelectedListener(navigationItemSelectedListener)
        bottomNavigationView.setOnItemReselectedListener(navigationItemReselectedListener)

    }

    private fun openScreen(fragment: Fragment) {
        childFragmentManager.beginTransaction().apply {
            replace(R.id.view_fragment_container, fragment, fragment::class.java.simpleName)
            commit()
        }
    }
}