package com.tahir.marvelapp.data.models.events

import com.google.gson.annotations.SerializedName


data class Previous (

  @SerializedName("resourceURI" ) var resourceURI : String? = null,
  @SerializedName("name"        ) var name        : String? = null

)