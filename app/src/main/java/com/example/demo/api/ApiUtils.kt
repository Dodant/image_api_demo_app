package com.example.demo.api

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.ui.graphics.asImageBitmap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import com.example.demo.R
import com.example.demo.viewmodel.ImageViewModel
import java.time.Duration
import java.time.Instant

@SuppressLint("UseCompatLoadingForDrawables")
fun getImageMultipart(context: Context, resourceId: Int): MultipartBody.Part {
    val drawable = context.resources.getDrawable(resourceId, null) as BitmapDrawable
    val bitmap = drawable.bitmap
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    val byteArray = stream.toByteArray()

    val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), byteArray)
    return MultipartBody.Part.createFormData("image", "image_$resourceId.jpg", requestFile)
}

fun sendPostRequest(context: Context, selectedOption: Int, method: String, viewModel: ImageViewModel) {
    val start = Instant.now()
    val apiService = RetrofitClient.instance.create(ApiService::class.java)
    val imagePart: MultipartBody.Part? = when (selectedOption) {
        1 -> getImageMultipart(context, R.drawable.test1)
        2 -> getImageMultipart(context, R.drawable.test2)
        3 -> getImageMultipart(context, R.drawable.test3)
        4 -> getImageMultipart(context, R.drawable.test4)
        5 -> getImageMultipart(context, R.drawable.test5)
        6 -> getImageMultipart(context, R.drawable.test6)
        7 -> getImageMultipart(context, R.drawable.test7)
        8 -> getImageMultipart(context, R.drawable.test8)
        9 -> getImageMultipart(context, R.drawable.test9)
        10 -> getImageMultipart(context, R.drawable.test10)
        11 -> getImageMultipart(context, R.drawable.test11)
        12 -> getImageMultipart(context, R.drawable.test12)
        13 -> getImageMultipart(context, R.drawable.test13)
        14 -> getImageMultipart(context, R.drawable.test14)
        15 -> getImageMultipart(context, R.drawable.test15)
        16 -> getImageMultipart(context, R.drawable.test16)
        17 -> getImageMultipart(context, R.drawable.test17)
        18 -> getImageMultipart(context, R.drawable.test18)
        19 -> getImageMultipart(context, R.drawable.test19)
        20 -> getImageMultipart(context, R.drawable.test20)
        21 -> getImageMultipart(context, R.drawable.test21)

//        22 -> getImageMultipart(context, R.drawable.test22)
//        23 -> getImageMultipart(context, R.drawable.test23)
//        24 -> getImageMultipart(context, R.drawable.test24)
//        25 -> getImageMultipart(context, R.drawable.test25)
//        26 -> getImageMultipart(context, R.drawable.test26)
//        27 -> getImageMultipart(context, R.drawable.test27)
//        28 -> getImageMultipart(context, R.drawable.test28)
//        29 -> getImageMultipart(context, R.drawable.test29)
        else -> null
    }
    val methodPart = method.toRequestBody("text/plain".toMediaTypeOrNull())

    val call = imagePart?.let { apiService.postData(methodPart, it) }
    call?.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                Log.i("Retrofit", "POST request successful: ${response.code()}")
                val bitmap = BitmapFactory.decodeStream(response.body()?.byteStream())
                val imageBitmap = bitmap.asImageBitmap()
                viewModel.imageBitmap.value = imageBitmap
                val end = Instant.now()
                val duration = Duration.between(start, end).toMillis()
                Log.i("AIButton", duration.toString())
            } else {
                Log.e("Retrofit", "POST request failed: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Log.e("Retrofit", "POST request error: ${t.message}")
        }
    })
}
