package com.tahir.marvelapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tahir.marvelapp.constants.WebServiceConstants.Companion.PAGE_SIZE
import com.tahir.marvelapp.constants.WebServiceConstants.Companion.STARTING_OFFSET_INDEX
import com.tahir.marvelapp.data.commonDTOs.MarvelCharacter
import com.tahir.marvelapp.data.repo.Repository
import retrofit2.HttpException
import java.io.IOException

class MarvelCharactersDataSource(private val repository: Repository) :
    PagingSource<Int, MarvelCharacter>() {

    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
        return state.anchorPosition
    }

    /**
     * setting up the offset and prev key,next key, and loads the page data from the repository
     * @param params
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        val offset = params.key ?: STARTING_OFFSET_INDEX

        return try {
            val characterList =
                repository.getPaginatedCharacters(offset = offset, limit = PAGE_SIZE)
            LoadResult.Page(
                data = characterList,
                prevKey = if (offset == STARTING_OFFSET_INDEX) null else offset - PAGE_SIZE,
                nextKey = if (characterList!!.size != 0) offset + PAGE_SIZE else null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


}