package com.mdfirst.core.metrics

import android.content.res.Resources
import kotlin.math.roundToInt

fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).roundToInt()