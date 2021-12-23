package com.mdfirst

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.mdfirst.dailyimage.ui.DailyImageFragment
import com.mdfirst.motionlayoutsample.MotionLayoutFragment
import com.mdfirst.scrollableappbar.PurchaseDetailFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // call before set content view
        setTheme(DailyImageFragment.currentTheme)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MotionLayoutFragment>(R.id.fragment_container)
            }
        }
    }
}