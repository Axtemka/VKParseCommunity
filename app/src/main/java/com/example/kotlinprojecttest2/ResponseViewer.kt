import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinprojecttest2.data.remote.quest.GetVKJsonResponse
import com.example.kotlinprojecttest2.data.remote.quest.QuestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ResponseViewer (private val platformName:String): ViewModel(){
    private val res_list: MutableList<MutableList<String>> = ArrayList()
    private val compositeDisposable = CompositeDisposable()
    lateinit var questApi: QuestApi

    fun returnUrls() {
        if (platformName == "vk") {
            vkConfigureRetrofit()
        }
        //        if (platformName == "reddit") {
        //            redditConfigureRetrofit()
        //        }
        //        if (platformName == "telegram") {
        //            telegramConfigureRetrofit()
        //        }
    }

    private fun vkConfigureRetrofit() {

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
        //questApi.getVkJson(domain = "memzavod1523l", access_token = "9fb466189fb466189fb46618449ca5442599fb49fb46618fce51db6b049cb80918bb78e", ver = "5.131")
        questApi = retrofit.create(QuestApi::class.java)
        viewModelScope.launch{
            try{
                questApi.getVkJson(domain = "memzavod1523l", access_token = "9fb466189fb466189fb46618449ca5442599fb49fb46618fce51db6b049cb80918bb78e", ver = "5.131")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        vkFindUrls(it)
                    }, {
                        //println(it.stackTrace.toString())
                    })
            }catch(e: Exception){
                e.localizedMessage?.let { Log.e("TAG", it) }
            }

        }
    }

    private fun redditConfigureRetrofit() {

//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//
//        //TODO(Add https for reddit api methods)
//        val retrofit = Retrofit.Builder()
//            .baseUrl("")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//
//        questApi = retrofit.create(QuestApi::class.java)
//
//        compositeDisposable.add(
//            questApi.getRedditJson()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    redditFindUrls(it)
//                }, {
//                    //println(it.stackTrace.toString())
//                })
//        )
    }

//    private fun telegramConfigureRetrofit() {
//
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//
//        //TODO(Add https for telegram api methods)
//        val retrofit = Retrofit.Builder()
//            .baseUrl("")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//
//        questApi = retrofit.create(QuestApi::class.java)
//
//        compositeDisposable.add(
//            questApi.getTelegramJson()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    telegramFindUrls(it)
//                }, {
//                    //println(it.stackTrace.toString())
//                })
//        )
//    }

    fun vkFindUrls(res: GetVKJsonResponse) {
        //TODO(Add return statement)

        res.vkResponse?.items?.forEach {
            if (it.marked_as_ads == 0) {
                val inter_list: MutableList<String> = ArrayList()
                // Checking for empty text
                inter_list.add(it.text)
                // Adding existing urls to intermediate list
                it.attachments?.forEach {
                    //                    if (it.type == "photo"){
                    it?.photo?.sizes?.last()?.url?.let { it1 -> inter_list.add(it1) }
                    //                    }
                }
                // checking for empty intermediate list
                if (!inter_list.isNullOrEmpty()) {
                    res_list.add(inter_list)
                }
            }
        }
        // debug output (comment if not necessary)
        Log.e("TAAAAG", res_list.toString())
    }

    //TODO(Replace string with reddit data class response)
    fun redditFindUrls(res: String) {

        //TODO(Add to QuestApi and data.remote.quest reddit methods)
        //TODO(Add return statement)



        //        res.redditResponse?.items?.forEach {
        //            if (it.marked_as_ads == 0) {
        //                val inter_list: MutableList<String> = ArrayList()
        //
        //                // Checking for empty text
        //
        //                inter_list.add(it.text)
        //
        //
        //                // Adding existing urls to intermediate list
        //                it.attachments?.forEach {
        //                      if (it.type == "photo"){
        //                    it?.photo?.sizes?.last()?.url?.let { it1 -> inter_list.add(it1) }
        //                      }
        //
        //                }
        //
        //                // checking for empty intermediate list
        //                if (!inter_list.isNullOrEmpty()) {
        //                    res_list.add(inter_list)
        //                }
        //            }
        //        }

        // debug output (comment if not necessary)
        //Log.e("TAAAAG", res_list.toString())
    }

    //TODO(Replace string with telegram data class response)
     fun telegramFindUrls(res: String) {

        //TODO(Add to QuestApi and data.remote.quest telegram methods)
        //TODO(Add return statement)



        //        res.redditResponse?.items?.forEach {
        //            if (it.marked_as_ads == 0) {
        //                val inter_list: MutableList<String> = ArrayList()
        //
        //                // Checking for empty text
        //
        //                inter_list.add(it.text)
        //
        //
        //                // Adding existing urls to intermediate list
        //                it.attachments?.forEach {
        //                      if (it.type == "photo"){
        //                    it?.photo?.sizes?.last()?.url?.let { it1 -> inter_list.add(it1) }
        //                      }
        //
        //                }
        //
        //                // checking for empty intermediate list
        //                if (!inter_list.isNullOrEmpty()) {
        //                    res_list.add(inter_list)
        //                }
        //            }
        //        }
        //        debug output (comment if not necessary)
        //        Log.e("TAAAAG", res_list.toString())
    }

    fun getList(): MutableList<MutableList<String>> {
        return res_list
    }
}
