package com.tahir.marvelapp.fakeRepository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tahir.marvelapp.api.MarvelService
import com.tahir.marvelapp.data.models.characters.BaseCharacters
import com.tahir.marvelapp.data.models.comics.BaseComic
import com.tahir.marvelapp.data.models.events.BaseEvents
import com.tahir.marvelapp.data.models.series.BaseSeries
import com.tahir.marvelapp.data.models.stories.BaseStories
import retrofit2.Response

/** fake Implementation for MarvelService */
class FakeRemoteRepoImpl() : MarvelService {

    override suspend fun gePaginatedCharacters(
        offset: Int,
        limit: Int,
        apikey: String,
        hash: String,
        ts: Int
    ): BaseCharacters {
        return Gson().fromJson<BaseCharacters>(SampleMarvelData.getCharacters())

    }

    override suspend fun getCharacterComics(
        id: Int,
        apikey: String,
        hash: String,
        ts: Int
    ): Response<BaseComic> {
        return Response.success(Gson().fromJson<BaseComic>(SampleMarvelData.getComicData()))

    }

    override suspend fun getCharacterSeries(
        id: Int,
        apikey: String,
        hash: String,
        ts: Int
    ): Response<BaseSeries> {
        return Response.success(Gson().fromJson<BaseSeries>(SampleMarvelData.getSeriesData()))
    }

    override suspend fun getCharacterStories(
        id: Int,
        apikey: String,
        hash: String,
        ts: Int
    ): Response<BaseStories> {
        return Response.success(Gson().fromJson<BaseStories>(SampleMarvelData.getStoriesData()))
    }

    override suspend fun getCharacterEvents(
        id: Int,
        apikey: String,
        hash: String,
        ts: Int
    ): Response<BaseEvents> {
        return Response.success(Gson().fromJson<BaseEvents>(SampleMarvelData.getEventsData()))
    }


    inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)

}
