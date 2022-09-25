package com.tahir.marvelapp.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.tahir.marvelapp.data.commonDTOs.CharacterDetail
import com.tahir.marvelapp.data.commonDTOs.MarvelCharacter
import com.tahir.marvelapp.data.db.MarvelAppDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**a
 * holds all the local database operations
 * @constructor injection marvelAppDao
 */
class LocalDataSource @Inject constructor(private val marvelAppDao: MarvelAppDao) {
    fun getCharactersFromDb(): PagingSource<Int, MarvelCharacter> {
        return marvelAppDao.getAllCharacters()

    }

    suspend fun addMarvelCharacter(marvelCharacters: ArrayList<MarvelCharacter>) {
        marvelAppDao.addCharacters(marvelCharacters)

    }

    suspend fun deleteAllCharacters() {
        return marvelAppDao.deleteAllCharacter()

    }

    suspend fun addMarvelCharacterDetails(marvelDetails: ArrayList<CharacterDetail>) =
        marvelAppDao.addMarvelDetails(marvelDetails)



    fun getMarvelCharacterDetailsFromId(id: Int): Flow<List<CharacterDetail>> {
        return marvelAppDao.getAllCharactersDetailsFromId(id)
    }

}
