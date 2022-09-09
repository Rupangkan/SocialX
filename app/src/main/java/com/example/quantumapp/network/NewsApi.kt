package com.example.quantumapp.network

import com.example.quantumapp.common.Constants.BASE_URL
import com.example.quantumapp.models.News
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface NewsApi {

//    @FormUrlEncoded
//    @POST("{acc_sid}/Messages")
//    fun sendMessage(@Path(value = "acc_sid") acc_sid: String, @Header("Authorization") auth: String, @FieldMap message: Map<String, String>): Call<ResponseBody>

    @GET("v2/everything?p={topic}&apiKey={apiKey}")
    suspend fun getNews(@Path(value = "topic") topic: String, @Path(value = "apiKey") apiKey: String): Response<News>


    companion object {

        private val interceptor = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor) // same for .addInterceptor(...)
            .build()


        operator fun invoke(): NewsApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi::class.java)
        }
    }
}