package com.tahir.marvelapp.data.models.stories

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)