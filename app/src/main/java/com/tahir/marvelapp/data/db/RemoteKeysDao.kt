package com.tahir.marvelapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {
    @Query("SELECT * FROM marvelcharacter WHERE id=:id")
    fun getRemotekeys(id: Int): MarvelRemoteKeys

    // handles the data and handles the conflict by replacing
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: ArrayList<MarvelRemoteKeys>)

    //delete all keys
    @Query("DELETE FROM MarvelRemoteKeys")
    suspend fun deleteAllRemoteKeys()


}