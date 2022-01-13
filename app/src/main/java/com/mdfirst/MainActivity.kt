package com.mdfirst

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import com.mdfirst.dailyimage.ui.DailyImageFragment
import com.mdfirst.recyclerview.ui.RecyclerViewSampleFragment
import com.mdfirst.textsamples.ui.TextSampleFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // call before set content view
        setTheme(DailyImageFragment.currentTheme)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<TextSampleFragment>(R.id.fragment_container)
            }
        }
    }
}