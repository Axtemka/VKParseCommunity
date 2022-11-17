package com.example.kotlinprojecttest2.data.remote.quest

import com.google.gson.annotations.SerializedName

data class GetVKJsonResponse(
    @SerializedName("response")
    val vkResponse: Response
)