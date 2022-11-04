package com.example.kotlinprojecttest2.data.remote.quest


data class getJsonResponse(val items: List<Item>)


data class Item(
    val attachments: List<Attachment>,
    val id: Int,
    val text: String,
)
data class Attachment(
    val link: Link,
    val photo: PhotoX,
    //val type: String,
)
data class Link(
    val caption: String,
    val description: String,
    val photo: Photo,
    val title: String,
    val url: String
)
data class PhotoX(
    val access_key: String,
    val sizes: List<Size>,
    val text: String,
    val user_id: Int
)
data class Photo(
    val album_id: Int,
    val date: Int,
    val has_tags: Boolean,
    val id: Int,
    val owner_id: Int,
    val sizes: List<Size>,
    val text: String,
    val user_id: Int
)
data class Size(
    val height: Int,
    val type: String,
    val url: String,
    val width: Int
)