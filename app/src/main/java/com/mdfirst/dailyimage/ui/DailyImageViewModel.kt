package com.mdfirst.dailyimage.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdfirst.BuildConfig
import com.mdfirst.dailyimage.data.response.NASAImageResponse
import com.mdfirst.dailyimage.data.retrofit.NasaApiRetrofit
import com.mdfirst.dailyimage.domain.DailyImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyImageViewModel(
    private val liveDataForViewToObserve: MutableLiveData<DailyImage> = MutableLiveData(),
    private val retrofitImpl: NasaApiRetrofit = NasaApiRetrofit(),
) :
    ViewModel() {

    fun getImageData(): LiveData<DailyImage> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = DailyImage.Loading(null)

        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            DailyImage.Error(Throwable("You need API key"))
        } else {
            executeImageRequest(apiKey)
        }
    }

    private fun executeImageRequest(apiKey: String) {
        val callback = object : Callback<NASAImageResponse> {

            override fun onResponse(
                call: Call<NASAImageResponse>,
                response: Response<NASAImageResponse>
            ) {
                handleImageResponse(response)
            }

            override fun onFailure(call: Call<NASAImageResponse>, t: Throwable) {
                liveDataForViewToObserve.value = DailyImage.Error(t)
            }
        }

        retrofitImpl.getNasaService().getImage(apiKey).enqueue(callback)
    }

    private fun handleImageResponse(response: Response<NASAImageResponse>) {
        if (response.isSuccessful && response.body() != null) {
            liveDataForViewToObserve.value = DailyImage.Success(response.body()!!)
            return
        }

        val message = response.message()
        if (message.isNullOrEmpty()) {
            liveDataForViewToObserve.value = DailyImage.Error(Throwable("Unidentified error"))
        } else {
            liveDataForViewToObserve.value = DailyImage.Error(Throwable(message))
        }
    }
}