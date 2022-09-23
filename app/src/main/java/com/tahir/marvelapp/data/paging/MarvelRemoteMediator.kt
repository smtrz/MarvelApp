package com.tahir.marvelapp.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.tahir.marvelapp.constants.WebServiceConstants
import com.tahir.marvelapp.data.commonDTOs.MarvelCharacter
import com.tahir.marvelapp.data.db.AppDatabase
import com.tahir.marvelapp.data.db.MarvelRemoteKeys
import com.tahir.marvelapp.data.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@ExperimentalPagingApi
class MarvelRemoteMediator(
    private val repository: Repository,
    private val MarvelDatabase: AppDatabase

) : RemoteMediator<Int, MarvelCharacter>() {
    // var currentOffset = 0
    val marvelDao = MarvelDatabase.marvelAppDao()
    val remoteKeysDao = MarvelDatabase.remoteKeysDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MarvelCharacter>
    ): MediatorResult {
        //Fetch marvel characters from remote
        //Save these + remotekys data into the db
        //logic for states - REFRESH - PREPEND,APPEND
        return try {

            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    Log.e("##", "refresh is called.")

                    val remoteKeys = getRemoteKeyClosetToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 0

                }
                LoadType.PREPEND ->
                {
                    Log.e("##", "pepend is called.")

                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage


                }
                LoadType.APPEND -> {
                    Log.e("##", "append is called.")
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }

            }
            //  val currentPage = 1
            val response =
                repository.getPaginatedCharacters(
                    currentPage * WebServiceConstants.PAGE_SIZE,
                    WebServiceConstants.PAGE_SIZE
                )

            //      currentOffset = (currentPage*WebServiceConstants.PAGE_SIZE)


            val endOfPaginationReached = response.size == 0

            val prevPage =
                if (currentPage == 0) null else currentPage - 1
            val nextPage =
                if (endOfPaginationReached) null else currentPage + 1
            MarvelDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {

                    withContext(Dispatchers.IO) {
                        marvelDao.deleteAllCharacter()
                        remoteKeysDao.deleteAllRemoteKeys()


                    }

                }
                withContext(Dispatchers.IO) {
                    marvelDao.addCharacters(response)
                }
                val keys = response.map { marvel ->
                    MarvelRemoteKeys(id = marvel.id!!, prevPage = prevPage, nextPage = nextPage)
                }
                withContext(Dispatchers.IO) {
                    remoteKeysDao.addAllRemoteKeys(keys as ArrayList<MarvelRemoteKeys>)
                }
            }
            MediatorResult.Success(endOfPaginationReached)

        } catch (e: Exception) {
            Log.d("##", "Error tahir")
            MediatorResult.Error(e)

        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MarvelCharacter>): MarvelRemoteKeys? {
        return state.pages.firstOrNull() { it.data.isNotEmpty() }?.data?.first()
            ?.let { character ->
                withContext(Dispatchers.IO) {
                    remoteKeysDao.getRemotekeys(id = character.id!!)
                }
            }

    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MarvelCharacter>): MarvelRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                withContext(Dispatchers.IO) {
                    remoteKeysDao.getRemotekeys(id = character.id!!)
                }
            }

    }

    private suspend fun getRemoteKeyClosetToCurrentPosition(state: PagingState<Int, MarvelCharacter>): MarvelRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                withContext(Dispatchers.IO) {
                    remoteKeysDao.getRemotekeys(id = id)
                }
            }

        }

    }
}