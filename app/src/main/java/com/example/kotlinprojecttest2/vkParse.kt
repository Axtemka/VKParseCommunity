package com.example.kotlinprojecttest2


import com.example.kotlinprojecttest2.data.remote.quest.GetJsonResponse
import com.example.kotlinprojecttest2.data.remote.quest.QuestApi

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class vkParse() {
    val res_list: MutableList<MutableList<String>> = ArrayList()

    init {
        configureRetrofit()
    }
    private fun configureRetrofit(){

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.vk.com/method/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val questApi: QuestApi = retrofit.create(QuestApi::class.java)
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(questApi.getVkJson()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //findUrls(it)
            },{
                //println(it.stackTrace.toString())
            })
        )}

    fun findUrls(res : GetJsonResponse) {
        res.resp?.items?.forEach {
            if(it.marked_as_ads==0){
                val inter_list : MutableList<String> = ArrayList()

                // Checking for empty text
                if(it.text.isNotEmpty()){
                    inter_list.add(it.text)
                }

                // Adding existing urls to intermediate list
                it.attachments?.forEach {
                    it?.photo?.sizes?.last()?.url?.let { it1 -> inter_list.add(it1) }
                }

                // checking for empty intermediate list
                if (inter_list.isNotEmpty()){
                    res_list.add(inter_list)
                }
            }
        }
        // debug output (comment if not necessary)
        //Log.e("URLS_LIST", res_list.toString())
    }
    fun getList(): MutableList<MutableList<String>> {
        return res_list
    }
}