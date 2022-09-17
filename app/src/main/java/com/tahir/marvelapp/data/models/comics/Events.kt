package com.tahir.marvelapp.data.models.comics

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)