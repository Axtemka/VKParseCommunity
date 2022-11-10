package com.example.kotlinprojecttest2.data.remote.quest

import com.google.gson.annotations.SerializedName

data class GetJsonResponse(
    @SerializedName("response")
    val resp: Response
)