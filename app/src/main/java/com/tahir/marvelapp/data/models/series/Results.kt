package com.tahir.marvelapp.data.models.series

import com.google.gson.annotations.SerializedName
import com.tahir.marvelapp.data.models.comics.Characters
import com.tahir.marvelapp.data.models.comics.Creators
import com.tahir.marvelapp.data.models.comics.Events
import com.tahir.marvelapp.data.models.comics.Stories
import com.tahir.marvelapp.data.models.comics.Thumbnail
import com.tahir.marvelapp.data.models.comics.Urls
import com.tahir.marvelapp.data.models.events.Comics


data class Results (

    @SerializedName("id"          ) var id          : Int?            = null,
    @SerializedName("title"       ) var title       : String?         = null,
    @SerializedName("description" ) var description : String?         = null,
    @SerializedName("resourceURI" ) var resourceURI : String?         = null,
    @SerializedName("urls"        ) var urls        : ArrayList<Urls> = arrayListOf(),
    @SerializedName("startYear"   ) var startYear   : Int?            = null,
    @SerializedName("endYear"     ) var endYear     : Int?            = null,
    @SerializedName("rating"      ) var rating      : String?         = null,
    @SerializedName("type"        ) var type        : String?         = null,
    @SerializedName("modified"    ) var modified    : String?         = null,
    @SerializedName("thumbnail"   ) var thumbnail   : Thumbnail?      = Thumbnail(),
    @SerializedName("creators"    ) var creators    : Creators?       = Creators(),
    @SerializedName("characters"  ) var characters  : Characters?     = Characters(),
    @SerializedName("stories"     ) var stories     : Stories?        = Stories(),
    @SerializedName("comics"      ) var comics      : Comics?         = Comics(),
    @SerializedName("events"      ) var events      : Events?         = Events(),
    @SerializedName("next"        ) var next        : String?         = null,
    @SerializedName("previous"    ) var previous    : String?         = null

)