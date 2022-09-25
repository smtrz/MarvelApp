package com.tahir.marvelapp.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tahir.marvelapp.data.commonDTOs.CharacterDetail
import com.tahir.marvelapp.data.commonDTOs.MarvelCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelAppDao {
    // selects all the marvel data
    @Query("SELECT * FROM marvelcharacter")
    fun getAllCharacters(): PagingSource<Int, MarvelCharacter>

    // handles the data insertion and handles the conflict by replacing
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(marvelCharacter: ArrayList<MarvelCharacter>): List<Long>

    // deletes the data in the database
    @Query("DELETE FROM MarvelCharacter")
    suspend fun deleteAllCharacter()

    // handles the data insertion and handles the conflict by replacing
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMarvelDetails(marvelDetails: ArrayList<CharacterDetail>)

    // selects the data from id
    @Query("SELECT * FROM CharacterDetail WHERE marvel_id=:id ")
    fun getAllCharactersDetailsFromId(id: Int): Flow<List<CharacterDetail>>
}
