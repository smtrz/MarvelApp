package com.tahir.marvelapp.data.repo

import com.tahir.marvelapp.data.commonDTOs.MarvelCharacter
import com.tahir.marvelapp.data.models.comics.BaseComic
import com.tahir.marvelapp.data.models.events.BaseEvents
import com.tahir.marvelapp.data.models.series.BaseSeries
import com.tahir.marvelapp.data.models.stories.BaseStories
import com.tahir.marvelapp.extensions.applyCommonSideEffects
import com.tahir.marvelapp.generics.BaseApiResponse
import com.tahir.marvelapp.generics.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository is the Single Source of truth that contains instance of remote data
 * source inherits from BaseApiResponse.
 * @constructor remoteDataSource (using construction injection)
 */
@Singleton
class Repository
@Inject
constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    /**
     * calls API for paginated marvel characters
     * @param offset, limit
     * @return Species
     */
    suspend fun getPaginatedCharacters(offset: Int, limit: Int): ArrayList<MarvelCharacter> {
        return remoteDataSource.getPaginatedMarvels(offset, limit)

    }

    suspend fun getCharacterComics(id: Int): Flow<ResponseResult<BaseComic>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getCharacterComics(id) })
        }.flowOn(Dispatchers.IO)
            .applyCommonSideEffects().catch {
                emit(ResponseResult.Error("Error occured"))
            }

    }

    suspend fun getCharacterEvents(id: Int): Flow<ResponseResult<BaseEvents>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getCharacterEvents(id) })
        }.flowOn(Dispatchers.IO)
            .applyCommonSideEffects().catch {
                emit(ResponseResult.Error("Error occured"))
            }

    }

    suspend fun getCharacterStories(id: Int): Flow<ResponseResult<BaseStories>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getCharacterStories(id) })
        }.flowOn(Dispatchers.IO)
            .applyCommonSideEffects().catch {
                emit(ResponseResult.Error("Error occured"))
            }

    }

    fun getCharacterSeries(id: Int): Flow<ResponseResult<BaseSeries>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getCharacterSeries(id) })
        }.flowOn(Dispatchers.IO)
            .applyCommonSideEffects().catch {
                emit(ResponseResult.Error("Error occured"))
            }
    }


}
