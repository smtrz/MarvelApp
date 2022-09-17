package com.tahir.marvelapp.data.models.events

data class BaseEvents(
    val attributionHTML: String?=null,
    val attributionText: String?=null,
    val code: Int?=null,
    val copyright: String?=null,
    val `data`: Data?=null,
    val etag: String?=null,
    val status: String?=null
)