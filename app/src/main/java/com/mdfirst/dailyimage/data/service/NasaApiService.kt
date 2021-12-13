package com.mdfirst.dailyimage.data.service

import com.mdfirst.dailyimage.data.response.NASAImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {

    @GET("planetary/apod")
    fun getImage(@Query("api_key") apiKey: String): Call<NASAImageResponse>
}