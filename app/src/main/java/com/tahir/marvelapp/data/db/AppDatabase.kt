package com.tahir.marvelapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tahir.marvelapp.data.commonDTOs.MarvelCharacter
import com.tahir.shortlyapp.db.MarvelAppDao

/**
 * abstract class to setup app database and contains the abstract method for db operations - Data access object.
 */
@Database(entities = [MarvelCharacter::class, MarvelRemoteKeys::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun marvelAppDao(): MarvelAppDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}