package com.tahir.shortlyapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tahir.marvelapp.data.commonDTOs.MarvelCharacter

@Dao
interface MarvelAppDao {
    // selects all the marvel data and order by id
    @Query("SELECT * FROM marvelcharacter order by id DESC")
    fun getAllCharacters(): LiveData<ArrayList<MarvelCharacter>>

    // handles the data and handles the conflict by replacing
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLink(marvelCharacter: MarvelCharacter): Long


}
