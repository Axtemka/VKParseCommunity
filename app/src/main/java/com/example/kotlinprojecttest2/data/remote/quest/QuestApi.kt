package com.example.kotlinprojecttest2.data.remote.quest

import io.reactivex.Single
import retrofit2.http.*


interface QuestApi {
    @GET("./wall.get?domain=memzavod1523l&count=100&access_token=9fb466189fb466189fb46618449ca5442599fb49fb46618fce51db6b049cb80918bb78e&v=5.131")
    fun getVkJson() : Single<GetVKJsonResponse>
    //@Field("domain") domain: String, @Field("access_token") access_token:String, @Field("v") ver: String
    //TODO(Reddit parsing)   (add data classes to reddit fun)
    fun getRedditJson() : Single<String>
    //TODO(Telegram parsing) (add data classes to telegram fun)
    fun getTelegramJson() : Single<String>
}