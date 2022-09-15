package com.tahir.marvelapp.data.models.comics

import com.google.gson.annotations.SerializedName


data class Prices (

  @SerializedName("type"  ) var type  : String? = null,
  @SerializedName("price" ) var price : Double? = null

)