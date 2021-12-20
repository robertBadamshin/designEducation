package com.mdfirst.dailyimage.domain

import com.mdfirst.dailyimage.data.response.NASAImageResponse

sealed class DailyImage {

    data class Success(val serverResponseData: NASAImageResponse) : DailyImage()

    data class Error(val error: Throwable) : DailyImage()

    data class Loading(val progress: Int?) : DailyImage()
}