package com.tahir.marvelapp.api

import com.tahir.marvelapp.BuildConfig
import com.tahir.marvelapp.constants.WebServiceConstants
import com.tahir.marvelapp.data.models.characters.BaseCharacters
import com.tahir.marvelapp.data.models.comics.BaseComic
import com.tahir.marvelapp.data.models.events.BaseEvents
import com.tahir.marvelapp.data.models.series.BaseSeries
import com.tahir.marvelapp.data.models.stories.BaseStories
import com.tahir.marvelapp.utils.Helper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Marvel Service contains suspend methods for the network calls
 */
interface MarvelService {
    /**
     * getPaginated Marvel Characters
     * @param offset , limit
     * @return Characters.
     */
    @GET(WebServiceConstants.GET_CHARACTERS)
    suspend fun gePaginatedCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("apikey") apikey: String = BuildConfig.MARVEL_PUBLIC_KEY,
        @Query("hash") hash: String = Helper.getHash(),
        @Query("ts") ts: Int = 1,

        ): BaseCharacters

    /**
     * get comics from the id of marvel character
     * @param id , apikey,hash,ts
     * @return BaseComic.
     */

    @GET(WebServiceConstants.GET_COMICS)
    suspend fun getCharacterComics(
        @Path("id") id: Int,
        @Query("apikey") apikey: String = BuildConfig.MARVEL_PUBLIC_KEY,
        @Query("hash") hash: String = Helper.getHash(),
        @Query("ts") ts: Int = 1,

        ): Response<BaseComic>

    /**
     * get series from the id of marvel character
     * @param id , apikey,hash,ts
     * @return BaseSeries.
     */

    @GET(WebServiceConstants.GET_SERIES)
    suspend fun getCharacterSeries(
        @Path("id") id: Int,
        @Query("apikey") apikey: String = BuildConfig.MARVEL_PUBLIC_KEY,
        @Query("hash") hash: String = Helper.getHash(),
        @Query("ts") ts: Int = 1,

        ): Response<BaseSeries>

    /**
     * get stories from the id of marvel character
     * @param id , apikey,hash,ts
     * @return BaseStories.
     */

    @GET(WebServiceConstants.GET_STORIES)
    suspend fun getCharacterStories(
        @Path("id") id: Int,
        @Query("apikey") apikey: String = BuildConfig.MARVEL_PUBLIC_KEY,
        @Query("hash") hash: String = Helper.getHash(),
        @Query("ts") ts: Int = 1,

        ): Response<BaseStories>

    /**
     * get events from the id of marvel character
     * @param id , apikey,hash,ts
     * @return BaseStories.
     */

    @GET(WebServiceConstants.GET_EVENTS)
    suspend fun getCharacterEvents(
        @Path("id") id: Int,
        @Query("apikey") apikey: String = BuildConfig.MARVEL_PUBLIC_KEY,
        @Query("hash") hash: String = Helper.getHash(),
        @Query("ts") ts: Int = 1,

        ): Response<BaseEvents>
}
