package com.example.kotlinprojecttest2.data.remote.quest

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers


interface QuestApi {
    @GET("./wall.get?domain=memzavod1523l&access_token=9fb466189fb466189fb46618449ca5442599fb49fb46618fce51db6b049cb80918bb78e&v=5.131")
    fun get_vk_json() : Single<GetJsonResponse>
}