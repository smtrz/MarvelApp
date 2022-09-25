package com.tahir.marvelapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.tahir.marvelapp.constants.WebServiceConstants
import com.tahir.marvelapp.data.commonDTOs.MarvelCharacter
import com.tahir.marvelapp.data.db.AppDatabase
import com.tahir.marvelapp.data.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@ExperimentalPagingApi
class MarvelRemoteMediator(
    private val repository: Repository,
    private val MarvelDatabase: AppDatabase

) : RemoteMediator<Int, MarvelCharacter>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MarvelCharacter>
    ): MediatorResult {

        return try {
            //logic for states - REFRESH - PREPEND,APPEND

            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    0

                }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    // We must explicitly check if the last item is `null` when appending,
                    // since passing `null` to networkService is only valid for initial load.
                    // If lastItem is `null` it means no items were loaded after the initial
                    // REFRESH and there are no more items to load.
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)

                    lastItem.id
                }

            }

            val response =
                repository.getPaginatedCharacters(
                    currentPage as Int,
                    WebServiceConstants.PAGE_SIZE
                )

            MarvelDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {


                    withContext(Dispatchers.IO) {
                        repository.deleteAllCharactersFromDb()


                    }

                }
                withContext(Dispatchers.IO) {
                    repository.addMarvelCharactersToDb(response)
                }

            }
            return MediatorResult.Success(endOfPaginationReached = response?.isEmpty())

        } catch (e: Exception) {
            MediatorResult.Error(e)

        }
    }


}