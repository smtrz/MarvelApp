package com.tahir.marvelapp.data.commonDTOs

import com.tahir.marvelapp.data.models.comics.BaseComic
import com.tahir.marvelapp.data.models.events.BaseEvents
import com.tahir.marvelapp.data.models.series.BaseSeries
import com.tahir.marvelapp.data.models.stories.BaseStories

data class CharacterDetail(val Name: String? = null, val imageUrl: String? = null) {
    companion object {


        fun fromBaseClasstoDto(data: Any): ArrayList<CharacterDetail> {
            val characterDetail = arrayListOf<CharacterDetail>()
            when (data) {
                is BaseComic -> {
                    val comicResult = data.data?.results
                    if (comicResult != null) {
                        for (comicdata in comicResult) {
                            characterDetail.add(
                                CharacterDetail(
                                    comicdata.title,

                                    (comicdata.thumbnail?.path?.replace(
                                        "http:",
                                        "https:"
                                    ) + "." + comicdata.thumbnail?.extension)


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
                                    comicdata.title,


                                    (comicdata.thumbnail?.path?.replace(
                                        "http:",
                                        "https:"
                                    ) + "." + comicdata.thumbnail?.extension)


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
                                    comicdata.title,
                                    (comicdata.thumbnail?.path?.replace(
                                        "http:",
                                        "https:"
                                    ) + "." + comicdata.thumbnail?.extension)
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
                                    comicdata.title,
                                    (comicdata.thumbnail?.path?.replace(
                                        "http:",
                                        "https:"
                                    ) + "." + comicdata.thumbnail?.extension)

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