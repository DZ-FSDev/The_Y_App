package com.rrc.adev3007.pixel_perfect.the_y_app.data.models

data class Post(
    val name: String,
    val username: String,
    val profileImage: Int,
    val time: String,
    val content: String
)

data class CreatePostRequest(
    val api_key: String,
    val content: String,
    val email: String,
    val media_id: Int
)