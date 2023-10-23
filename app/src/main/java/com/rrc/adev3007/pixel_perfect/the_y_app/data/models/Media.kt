package com.rrc.adev3007.pixel_perfect.the_y_app.data.models

import com.google.gson.annotations.SerializedName

class Media {
    data class MediaCreate(
        val email:String,
        @SerializedName("api_key")
        val apiKey: String,
        val base64: String
    )

    data class MediaCreateResponse(
        val message: String,
        val id: Int
    )
}