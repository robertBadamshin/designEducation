package com.mdfirst.motionlayoutsample

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.mdfirst.R

class MotionLayoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_motion_layout, container, false)
    }
}