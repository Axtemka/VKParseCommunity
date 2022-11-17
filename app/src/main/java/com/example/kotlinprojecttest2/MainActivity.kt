package com.example.kotlinprojecttest2

import ResponseViewer
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.kotlinprojecttest2.data.remote.quest.GetJsonResponse
import com.example.kotlinprojecttest2.data.remote.quest.QuestApi
import androidx.appcompat.app.AppCompatActivity;
import com.example.kotlinprojecttest2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    // This will be connected with fragListTitles || platformName - Vk (test platform)
    private val viewer = ResponseViewer("vk")

    private val fragList = listOf(
        Vk.newInstance(),
        Telegram.newInstance(),
        Reddit.newInstance(),
        TikTok.newInstance()
    )
    private val fragListTitles = listOf(
        "Vk",
        "Telegram",
        "Reddit",
        "TikTok"
    )
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = VpAdapter(this, fragList)
        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.ourtablayout, binding.vp2){
            tab, pos -> tab.text = fragListTitles[pos]
        }.attach()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.vk -> {
                Toast.makeText(this, "Vk", Toast.LENGTH_SHORT).show()
            }
            R.id.telegram -> {
                Toast.makeText(this, "Telegram", Toast.LENGTH_SHORT).show()
            }
            R.id.reddit -> {
                Toast.makeText(this, "Reddit", Toast.LENGTH_SHORT).show()
            }
            R.id.tiktok -> {
                Toast.makeText(this, "TikTok", Toast.LENGTH_SHORT).show()
            }
            R.id.instagram -> {
                Toast.makeText(this, "Instagram", Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    fun vkParseOnClick(view: View){
        setContentView(R.layout.activity_main)
        //TODO(Different platform`s parsing from platform names (Reddit, Telegram))
        viewer.returnUrls()
    }
}

