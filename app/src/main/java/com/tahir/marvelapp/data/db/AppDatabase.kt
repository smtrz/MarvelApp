package com.tahir.marvelapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tahir.marvelapp.data.commonDTOs.CharacterDetail
import com.tahir.marvelapp.data.commonDTOs.MarvelCharacter

/**
 * abstract class to setup app database and contains the abstract method for db operations - Data access object.
 */
@Database(entities = [MarvelCharacter::class, CharacterDetail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun marvelAppDao(): MarvelAppDao
}