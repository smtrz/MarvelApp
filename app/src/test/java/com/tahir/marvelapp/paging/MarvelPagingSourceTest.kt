package com.tahir.marvelapp.paging

import androidx.paging.PagingSource
import com.tahir.marvelapp.Constants.WebServiceConstants
import com.tahir.marvelapp.data.paging.MarvelCharactersDataSource
import com.tahir.marvelapp.data.repo.RemoteDataSource
import com.tahir.marvelapp.data.repo.Repository
import com.tahir.marvelapp.fakeRepository.FakeRemoteRepoImpl
import com.tahir.marvelapp.helpers.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MarvelPagingSourceTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    lateinit var repository: Repository
    lateinit var fakeRepoImpl: FakeRemoteRepoImpl
    lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        fakeRepoImpl = FakeRemoteRepoImpl()
        remoteDataSource = RemoteDataSource(fakeRepoImpl)
        repository = Repository(remoteDataSource)
    }

    @Test
    fun successfulLoadOfItems() = runTest {
        val pagingSource = MarvelCharactersDataSource(repository)

        val expected =
            repository.getPaginatedCharacters(0, WebServiceConstants.PAGE_SIZE).data?.let {
                PagingSource.LoadResult.Page(
                    data = it.results,
                    prevKey = null,
                    nextKey = 100
                )
            }
        val actual =
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = WebServiceConstants.PAGE_SIZE,
                    placeholdersEnabled = false
                )
            )
        assertEquals(expected, actual)
    }
}
