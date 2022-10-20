package com.example.kotlinprojecttest2

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.vk.api.sdk.*
import com.vk.sdk.api.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun vkParseOnClick(view: View) {
        setContentView(R.layout.activity_main)
        val text: TextView = findViewById(R.id.text1)
    }
}