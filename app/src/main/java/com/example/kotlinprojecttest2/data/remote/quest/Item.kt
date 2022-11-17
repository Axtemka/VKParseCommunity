package com.example.kotlinprojecttest2.data.remote.quest

data class Item(
    val marked_as_ads: Int,
    var text: String,
    val attachments: List<Attachment>,
)