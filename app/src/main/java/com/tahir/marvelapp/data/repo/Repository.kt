package com.tahir.marvelapp.data.repo

import com.tahir.marvelapp.data.model.BaseCharacters
import com.tahir.marvelapp.generics.BaseApiResponse
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
    suspend fun getPaginatedCharacters(offset: Int, limit: Int): BaseCharacters {
        return remoteDataSource.getPaginatedMarvels(offset, limit)

    }


}