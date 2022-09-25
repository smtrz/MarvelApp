package com.tahir.marvelapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tahir.marvelapp.data.commonDTOs.CharacterDetail
import com.tahir.marvelapp.data.repo.Repository
import com.tahir.marvelapp.fakeRepository.FakeRemoteRepoImpl
import com.tahir.marvelapp.generics.ResponseResult
import com.tahir.marvelapp.helpers.MainCoroutineRule
import com.tahir.marvelapp.presenter.viewmodels.CharacterDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.fail
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MarvelDetailViewModelTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    @InjectMocks
    lateinit var fakeRepoImpl: FakeRemoteRepoImpl

    @InjectMocks
    lateinit var characterViewModel: CharacterDetailsViewModel

    val characterId = 1011334

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `marvel comic details are returned`() = runBlocking {
        try {


            `when`(repository.getCharacterComics(characterId))
                .thenReturn(
                    flow {
                        emit(
                            ResponseResult.Success(
                                fakeRepoImpl.getCharacterComics(characterId).body()!!
                            )
                        )
                    }
                )



            `when`(repository.getMarvelCharacterDetailsFromDbFromId(characterId))
                .thenReturn(
                    flow {
                        emit(

                            CharacterDetail.fromBaseClasstoDto(
                                fakeRepoImpl.getCharacterComics(characterId).body()!!, characterId
                            )
                        )
                    }
                )

            characterViewModel.getComicsFromCharacter(characterId)
            characterViewModel.getDataFromDb(characterId)
            Assert.assertTrue(characterViewModel.marvelDetails.value.filter {
                it.type.equals("comics")
            }.get(0).Name.equals("Avengers: The Initiative (2007) #19"))

        } catch (exception: Exception) {
            println(exception.message)
            fail()
        }
    }

    @Test
    fun `marvel event details are returned`() = runBlocking {
        try {

            `when`(repository.getCharacterEvents(characterId))
                .thenReturn(
                    flow {
                        emit(
                            ResponseResult.Success(
                                fakeRepoImpl.getCharacterEvents(characterId).body()!!
                            )
                        )
                    }
                )

            `when`(repository.getMarvelCharacterDetailsFromDbFromId(characterId))
                .thenReturn(
                    flow {
                        emit(

                            CharacterDetail.fromBaseClasstoDto(
                                fakeRepoImpl.getCharacterEvents(characterId).body()!!, characterId
                            )
                        )
                    }
                )
            characterViewModel.getEventsFromCharacter(characterId)
            characterViewModel.getDataFromDb(characterId)
            Assert.assertTrue(characterViewModel.marvelDetails.value.filter {
                it.type.equals("events")
            }.get(0).Name.equals("Secret Invasion"))


        } catch (exception: Exception) {
            println(exception.message)
            fail()
        }
    }

    @Test
    fun `marvel series details are returned`() = runBlocking {
        try {

            `when`(repository.getCharacterSeries(characterId))
                .thenReturn(
                    flow {
                        emit(
                            ResponseResult.Success(
                                fakeRepoImpl.getCharacterSeries(characterId).body()!!
                            )
                        )
                    }
                )

            `when`(repository.getMarvelCharacterDetailsFromDbFromId(characterId))
                .thenReturn(
                    flow {
                        emit(

                            CharacterDetail.fromBaseClasstoDto(
                                fakeRepoImpl.getCharacterSeries(characterId).body()!!, characterId
                            )
                        )
                    }
                )
            characterViewModel.getSeriesFromCharacter(characterId)
            characterViewModel.getDataFromDb(characterId)
            Assert.assertTrue(characterViewModel.marvelDetails.value.filter {
                it.type.equals("series")
            }.get(0).Name.equals("Avengers: The Initiative (2007 - 2010)"))


        } catch (exception: Exception) {
            println(exception.message)
            fail()
        }
    }


    @Test
    fun `marvel stories details are returned`() = runBlocking {
        try {

            `when`(repository.getCharacterStories(characterId))
                .thenReturn(
                    flow {
                        emit(
                            ResponseResult.Success(
                                fakeRepoImpl.getCharacterStories(characterId).body()!!
                            )
                        )
                    }
                )

            `when`(repository.getMarvelCharacterDetailsFromDbFromId(characterId))
                .thenReturn(
                    flow {
                        emit(

                            CharacterDetail.fromBaseClasstoDto(
                                fakeRepoImpl.getCharacterStories(characterId).body()!!, characterId
                            )
                        )
                    }
                )
            characterViewModel.getStoriesFromCharacter(characterId)
            characterViewModel.getDataFromDb(characterId)
            Assert.assertTrue(characterViewModel.marvelDetails.value.filter {
                it.type.equals("stories")
            }.get(0).Name.equals("Cover #19947"))

        } catch (exception: Exception) {
            println(exception.message)
            fail()
        }
    }

    @After
    fun tearDown() {
    }
}
