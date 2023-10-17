package com.rrc.adev3007.pixel_perfect.the_y_app.data

import com.rrc.adev3007.pixel_perfect.the_y_app.data.models.Post
import com.rrc.adev3007.pixel_perfect.the_y_app.data.models.CreatePostRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISynchronizer {
    @GET("post")
    suspend fun getPosts(
        @Query("email") email: String,
        @Query("api_key") api_key: String
    ) : Response<List<Post>>
    @POST("post")
    suspend fun createPost(@Body data: CreatePostRequest) : Response<Any>
}

private const val BASE_URL = "https://the-y-app-api.azurewebsites.net/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object Synchronizer {
    val api: ISynchronizer by lazy {
        retrofit.create(ISynchronizer::class.java)
    }
}
