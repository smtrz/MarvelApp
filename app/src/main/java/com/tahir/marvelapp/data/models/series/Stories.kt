package com.tahir.marvelapp.data.models.series

import com.google.gson.annotations.SerializedName
import com.tahir.marvelapp.data.models.comics.Items


data class Stories (

    @SerializedName("available"     ) var available     : Int?             = null,
    @SerializedName("collectionURI" ) var collectionURI : String?          = null,
    @SerializedName("items"         ) var items         : ArrayList<Items> = arrayListOf(),
    @SerializedName("returned"      ) var returned      : Int?             = null

)