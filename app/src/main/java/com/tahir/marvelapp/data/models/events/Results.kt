package com.tahir.marvelapp.data.models.events

import com.google.gson.annotations.SerializedName
import com.tahir.marvelapp.data.models.comics.Characters
import com.tahir.marvelapp.data.models.comics.Creators
import com.tahir.marvelapp.data.models.comics.Series
import com.tahir.marvelapp.data.models.comics.Stories
import com.tahir.marvelapp.data.models.comics.Thumbnail
import com.tahir.marvelapp.data.models.comics.Urls
import com.tahir.marvelapp.data.models.events.Comics
import com.tahir.marvelapp.data.models.events.Next
import com.tahir.marvelapp.data.models.events.Previous


data class Results (

    @SerializedName("id"          ) var id          : Int?            = null,
    @SerializedName("title"       ) var title       : String?         = null,
    @SerializedName("description" ) var description : String?         = null,
    @SerializedName("resourceURI" ) var resourceURI : String?         = null,
    @SerializedName("urls"        ) var urls        : ArrayList<Urls> = arrayListOf(),
    @SerializedName("modified"    ) var modified    : String?         = null,
    @SerializedName("start"       ) var start       : String?         = null,
    @SerializedName("end"         ) var end         : String?         = null,
    @SerializedName("thumbnail"   ) var thumbnail   : Thumbnail?      = Thumbnail(),
    @SerializedName("creators"    ) var creators    : Creators?       = Creators(),
    @SerializedName("characters"  ) var characters  : Characters?     = Characters(),
    @SerializedName("stories"     ) var stories     : Stories?        = Stories(),
    @SerializedName("comics"      ) var comics      : Comics?         = Comics(),
    @SerializedName("series"      ) var series      : Series?         = Series(),
    @SerializedName("next"        ) var next        : Next?           = Next(),
    @SerializedName("previous"    ) var previous    : Previous?       = Previous()

)