package com.mdfirst

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.*
import com.mdfirst.dailyimage.ui.DailyImageFragment
import com.mdfirst.textsamples.ui.TextSampleFragment
import java.util.concurrent.TimeUnit

private const val hideSplashAnimationDuration = 1000L

class MainActivity : AppCompatActivity() {

    private val splashScreenDuration = TimeUnit.SECONDS.toMillis(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreenStartMilliseconds = System.currentTimeMillis()
        val splashScreen = installSplashScreen()

        val keepSplashOnScreenCondition = SplashScreen.KeepOnScreenCondition {
            val currentTime = System.currentTimeMillis()
            val splashOnScreenMilliseconds = currentTime - splashScreenStartMilliseconds

            Log.d("SPLASH", "keepSplashOnScreenCondition")

            splashOnScreenMilliseconds < splashScreenDuration
        }

        val onExitAnimationListener = SplashScreen.OnExitAnimationListener { splashScreenViewProvider ->

            splashScreenViewProvider
                .view
                .animate()
                .alpha(0f)
                .setDuration(hideSplashAnimationDuration)
                .start()

            splashScreenViewProvider
                .iconView
                .animate()
                .alpha(0f)
                .setDuration(hideSplashAnimationDuration)
                .start()

            Log.d("SPLASH", "onExitAnimationListener")
            // splashScreenViewProvider.iconView
        }

        splashScreen.setOnExitAnimationListener(onExitAnimationListener)
        splashScreen.setKeepOnScreenCondition(keepSplashOnScreenCondition)

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