package com.tahir.marvelapp.api

import com.tahir.marvelapp.BuildConfig
import com.tahir.marvelapp.Constants.WebServiceConstants
import com.tahir.marvelapp.data.model.BaseCharacters
import com.tahir.marvelapp.data.model.Data
import com.tahir.marvelapp.utils.Helper
import retrofit2.http.GET
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
}
