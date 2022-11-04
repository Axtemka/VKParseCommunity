package com.example.kotlinprojecttest2

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinprojecttest2.data.remote.quest.QuestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

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

        val res = questApi.get_json()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }
}



//        var connection: HttpURLConnection? = null
//        val reader: BufferedReader
//        var line: String?
//        val responseContent = StringBuilder()
//        val text: TextView = findViewById(R.id.text1)
//        try {
//            val url = URL("https://vk.com/memzavod1523l")
//            connection = url.openConnection() as HttpURLConnection
//            connection.setRequestMethod("GET")
//            //connection.setRequestProperty();
//            connection.setConnectTimeout(10000)
//            connection.setReadTimeout(10000)
//            val status: Int = connection.getResponseCode()
//            if (status > 299) {
//                reader = BufferedReader(InputStreamReader(connection.getErrorStream()))
//                while (reader.readLine().also { line = it } != null) {
//                    responseContent.append(line)
//                }
//                reader.close()
//            } else {
//                reader = BufferedReader(InputStreamReader(connection.getInputStream()))
//                while (reader.readLine().also { line = it } != null) {
//                    responseContent.append(line)
//                }
//                reader.close()
//            }
//            println(responseContent.toString())
//            text.text = responseContent.toString()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        } finally {
//            if (connection != null) {
//                connection.disconnect()
//            }
//}
//
//}
//        val request: VKRequest<*> = VKRequest<Any?>("photos.getAll", getApiVersion())
//        request.params["owner_id:626417005"]
//        val res = VK.execute(request)
//        System.out.println(res.toString())
//        VKApiManager.execute(call)
//        val request: VKRequest<*> =
//            VKRequest<Any?>("users.get", getApiVersion())
//        get(VKParameters.from(VKApiConst.USER_IDS, "1,2"))
//        System.out.println(res)
//        text.text =
//        val call = VKMethodCall.Builder()
//            .method("photos.getAll")
//            .args("fields", "photo_200")
//            .version(getApiVersion())
//            .build()
//        val request: VKRequest<*> =
//            VKRequest<Any?>("friends.get", getApiVersion())