package com.tahir.marvelapp.data.commonDTOs

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tahir.marvelapp.data.models.comics.BaseComic
import com.tahir.marvelapp.data.models.events.BaseEvents
import com.tahir.marvelapp.data.models.series.BaseSeries
import com.tahir.marvelapp.data.models.stories.BaseStories
import kotlinx.android.parcel.Parcelize
/*
DTO - Only contains the fields required to show the list of characters.
 */
@Entity
@Parcelize
data class CharacterDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val marvel_id: Int? = null,
    val Name: String? = null,
    val imageUrl: String? = null,
    val type: String? = null
) : Parcelable {
    companion object {

        /*
        iterate via BaseComics and returns the list of comics.
         */
        fun fromBaseClasstoDto(data: Any, id: Int): ArrayList<CharacterDetail> {
            val characterDetail = arrayListOf<CharacterDetail>()
            when (data) {
                is BaseComic -> {
                    val comicResult = data.data?.results
                    if (comicResult != null) {
                        for (comicdata in comicResult) {
                            characterDetail.add(
                                CharacterDetail(
                                    Name = comicdata.title,
                                    marvel_id = id,
                                    imageUrl = (comicdata.thumbnail?.path?.replace(
                                        "http:",
                                        "https:"
                                    ) + "." + comicdata.thumbnail?.extension),
                                    type = "comics"


                                )
                            )

                        }
                    }

                }
                is BaseStories -> {

                    val comicResult = data.data?.results
                    if (comicResult != null) {
                        for (comicdata in comicResult) {


                            characterDetail.add(
                                CharacterDetail(
                                    Name = comicdata.title,
                                    marvel_id = id,

                                    imageUrl = (comicdata.thumbnail?.path?.replace(
                                        "http:",
                                        "https:"
                                    ) + "." + comicdata.thumbnail?.extension),
                                    type = "stories"


                                )
                            )

                        }
                    }

                }
                is BaseEvents -> {
                    val comicResult = data.data?.results
                    if (comicResult != null) {
                        for (comicdata in comicResult) {
                            characterDetail.add(
                                CharacterDetail(
                                    Name = comicdata.title,
                                    marvel_id = id,
                                    imageUrl = (comicdata.thumbnail?.path?.replace(
                                        "http:",
                                        "https:"
                                    ) + "." + comicdata.thumbnail?.extension),
                                    type = "events"
                                )
                            )

                        }
                    }

                }
                is BaseSeries -> {
                    val comicResult = data.data?.results
                    if (comicResult != null) {
                        for (comicdata in comicResult) {
                            characterDetail.add(
                                CharacterDetail(
                                    Name = comicdata.title,
                                    marvel_id = id,
                                    imageUrl = (comicdata.thumbnail?.path?.replace(
                                        "http:",
                                        "https:"
                                    ) + "." + comicdata.thumbnail?.extension), type = "series"
                                )
                            )

                        }
                    }

                }
            }
            return characterDetail
        }
    }
}