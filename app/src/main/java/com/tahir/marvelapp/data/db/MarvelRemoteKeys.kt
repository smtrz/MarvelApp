package com.tahir.marvelapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MarvelRemoteKeys(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val prevPage: Int?, val nextPage: Int?
)
