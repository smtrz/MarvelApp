package com.tahir.marvelapp.data.repo

import com.tahir.marvelapp.api.MarvelService
import javax.inject.Inject

/**
 * all operations related to fetching data from online sources are contained in RemoteDataSource
 * @constructor marvelService
 */
class RemoteDataSource @Inject constructor(private val marvelService: MarvelService) {
    suspend fun getPaginatedMarvels(offset: Int, limit: Int) =
        marvelService.gePaginatedCharacters(offset, limit)

    suspend fun getCharacterComics(id: Int) =
        marvelService.getCharacterComics(id)

    suspend fun getCharacterSeries(id: Int) =
        marvelService.getCharacterSeries(id)

    suspend fun getCharacterEvents(id: Int) =
        marvelService.getCharacterEvents(id)

    suspend fun getCharacterStories(id: Int) =
        marvelService.getCharacterStories(id)


}