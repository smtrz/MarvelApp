package com.tahir.marvelapp.data.model

import com.google.gson.annotations.SerializedName


data class Events(

    @SerializedName("available") var available: Int? = null,
    @SerializedName("collectionURI") var collectionURI: String? = null,
    @SerializedName("returned") var returned: Int? = null

)