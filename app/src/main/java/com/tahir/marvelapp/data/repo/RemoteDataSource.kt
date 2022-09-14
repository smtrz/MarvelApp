package com.tahir.marvelapp.data.repo

import com.tahir.marvelapp.api.MarvelService
import javax.inject.Inject

/**
 * all operations related to fetching data from online sources are contained in RemoteDataSource
 * @constructor pokemonService
 */
class RemoteDataSource @Inject constructor(private val marvelService: MarvelService) {
    suspend fun getPaginatedMarvels(offset: Int, limit: Int) =
        marvelService.gePaginatedCharacters(offset, limit)

}