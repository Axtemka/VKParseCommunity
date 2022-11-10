package com.example.kotlinprojecttest2.data.remote.quest

data class Photo(
    val access_key: String,
    val album_id: Int,
    val date: Int,
    val has_tags: Boolean,
    val id: Int,
    val owner_id: Int,
    val post_id: Int,
    val sizes: List<Size>,
    val text: String,

)