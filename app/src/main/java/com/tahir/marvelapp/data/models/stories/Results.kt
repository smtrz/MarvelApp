package com.tahir.marvelapp.data.models.stories

import com.google.gson.annotations.SerializedName
import com.tahir.marvelapp.data.models.comics.Characters
import com.tahir.marvelapp.data.models.comics.Creators
import com.tahir.marvelapp.data.models.comics.Events
import com.tahir.marvelapp.data.models.comics.Series
import com.tahir.marvelapp.data.models.events.Comics
import com.tahir.marvelapp.data.models.stories.OriginalIssue


data class Results (

    @SerializedName("id"            ) var id            : Int?           = null,
    @SerializedName("title"         ) var title         : String?        = null,
    @SerializedName("description"   ) var description   : String?        = null,
    @SerializedName("resourceURI"   ) var resourceURI   : String?        = null,
    @SerializedName("type"          ) var type          : String?        = null,
    @SerializedName("modified"      ) var modified      : String?        = null,
    @SerializedName("thumbnail"     ) var thumbnail     : String?        = null,
    @SerializedName("creators"      ) var creators      : Creators?      = Creators(),
    @SerializedName("characters"    ) var characters    : Characters?    = Characters(),
    @SerializedName("series"        ) var series        : Series?        = Series(),
    @SerializedName("comics"        ) var comics        : Comics?        = Comics(),
    @SerializedName("events"        ) var events        : Events?        = Events(),
    @SerializedName("originalIssue" ) var originalIssue : OriginalIssue? = OriginalIssue()

)