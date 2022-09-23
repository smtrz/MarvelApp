package com.tahir.shortlyapp.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tahir.marvelapp.data.commonDTOs.MarvelCharacter

@Dao
interface MarvelAppDao {
    // selects all the marvel data
    @Query("SELECT * FROM marvelcharacter")
    fun getAllCharacters(): PagingSource<Int, MarvelCharacter>

    // handles the data and handles the conflict by replacing
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(marvelCharacter: ArrayList<MarvelCharacter>)

    @Query("DELETE FROM MarvelCharacter")
    suspend fun deleteAllCharacter()


}
