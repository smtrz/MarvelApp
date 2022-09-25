package com.tahir.marvelapp

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import app.cash.turbine.test
import com.tahir.marvelapp.data.commonDTOs.CharacterDetail
import com.tahir.marvelapp.data.commonDTOs.MarvelCharacter
import com.tahir.marvelapp.data.db.AppDatabase
import com.tahir.marvelapp.data.db.MarvelAppDao
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTests {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var db: AppDatabase
    private lateinit var marvelAppDao: MarvelAppDao

    val characterList = arrayListOf<MarvelCharacter>(
        MarvelCharacter(
            id = 1,
            1001,
            "Tahir",
            "https://someimageurl.com"
        )
    )

    @Before
    fun setUp() {
        // setting up in memory database
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        // getting data access object from the database created.
        marvelAppDao = db.marvelAppDao()


    }

    /*
    Returns list of Long, for the successful inserts
     */
    @Test
    fun insertMarvelCharacterSuccess() = runBlocking {

        Assert.assertTrue(marvelAppDao.addCharacters(characterList).isNotEmpty())
    }

    /* i need to check this later on,
    test for paging source is causing some trouble

     */
//    @Test
//    fun testMarvelCharacterPagingSource() = runBlocking {
//        marvelAppDao.addCharacters(characterList)
//       // val pagingSource = marvelAppDao.getAllCharacters().getData()
//
//       assertEquals(PagingSource.LoadResult.Page(
//            data =characterList,
//            prevKey = null,
//            nextKey = characterList.get(0).id
//        ),
//            marvelAppDao.getAllCharacters().load(
//                PagingSource.LoadParams.Refresh(
//                    key = null,
//                    loadSize = 1,
//                    placeholdersEnabled = false
//                )
//            )
//        )
//
//
//    }
/*
Adds events, series, comics and get data from the flow from the database
 */
    @Test
    fun insertAndRetrieveMarvelCharacterDetailsSuccess() = runBlocking {
        val event_1 =
            CharacterDetail(id = 1, 1001, "event1", "https://someimageurl.com", type = "events")
        val events_2 =
            CharacterDetail(id = 2, 1001, "event2", "https://someimageurl.com", type = "events")
        val comic_1 =
            CharacterDetail(id = 3, 1001, "comic1", "https://someimageurl.com", type = "comics")
        val comic_2 =
            CharacterDetail(id = 4, 1001, "comic2", "https://someimageurl.com", type = "comics")
        var listOfMarvelDetails = arrayListOf(event_1, events_2, comic_1, comic_2)

        marvelAppDao.addMarvelDetails(listOfMarvelDetails)
        marvelAppDao.getAllCharactersDetailsFromId(1001).test {
            assertEquals(listOf(event_1, events_2, comic_1, comic_2), awaitItem())

        }


    }


    @After
    fun tearDown() {
        // closing the database after it has been used.
        db.close()

    }
}