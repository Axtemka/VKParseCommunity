package com.example.kotlinprojecttest2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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


class MainActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()
    lateinit var questApi: QuestApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun vkParseOnClick(view: View){
        setContentView(R.layout.activity_main)
        configureRetrofit()
    }
    private fun configureRetrofit(){
        val text: TextView = findViewById(R.id.text1)

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

        questApi = retrofit.create(QuestApi::class.java)

        compositeDisposable.add(questApi.get_vk_json()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                findUrls(it)
            },{
                //println(it.stackTrace.toString())
            })
        )}

    fun findUrls(res : GetJsonResponse) {

        val res_list: MutableList<MutableList<String>> = ArrayList()

        res.resp?.items?.forEach {
            if(it.marked_as_ads==0){
                val inter_list : MutableList<String> = ArrayList()

                // Checking for empty text
                if(!it.text.isNullOrEmpty()){
                    inter_list.add(it.text)
                }

                // Adding existing urls to intermediate list
                it.attachments?.forEach {
                    it?.photo?.sizes?.last()?.url?.let { it1 -> inter_list.add(it1) }
                }

                // checking for empty intermediate list
                if (!inter_list.isNullOrEmpty()){
                    res_list.add(inter_list)
                }
            }
        }
        // debug output (comment if not necessary)
        Log.e("URLS_LIST", res_list.toString())
    }
}
