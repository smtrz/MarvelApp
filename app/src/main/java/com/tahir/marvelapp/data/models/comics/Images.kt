package com.tahir.marvelapp.data.models.comics

import com.google.gson.annotations.SerializedName


data class Images (

  @SerializedName("path"      ) var path      : String? = null,
  @SerializedName("extension" ) var extension : String? = null

)