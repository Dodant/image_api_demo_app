package com.example.demo.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Multipart
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("/prx")
    fun postData(
        @Part("method") method: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<ResponseBody>
}