package com.tahir.marvelapp.presenter.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tahir.marvelapp.data.commonDTOs.CharacterDetail
import com.tahir.marvelapp.data.repo.Repository
import com.tahir.marvelapp.extensions.cancelIfActive
import com.tahir.marvelapp.generics.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {
    private var getCharacterDetailsJob: Job? = null
    var isComicLoading = mutableStateOf(false)
    var isEventLoading = mutableStateOf(false)
    var isSeriesLoading = mutableStateOf(false)
    var isStoriesLoading = mutableStateOf(false)

    val loadState: MutableLiveData<Boolean?> = MutableLiveData(false)
    var marvelDetails = mutableStateOf(emptyList<CharacterDetail>())

    fun getCharacterDetails(id: Int) {
        getCharacterDetailsJob.cancelIfActive()
        loadState.value = true
        getCharacterDetailsJob = viewModelScope.launch(Dispatchers.IO) {

            launch {
                getComicsFromCharacter(id)

            }
            launch {
                getStoriesFromCharacter(id)

            }
            launch {

                getSeriesFromCharacter(id)
            }
            launch {
                getEventsFromCharacter(id)
            }



            getDataFromDb(id)
        }

    }

     suspend fun getDataFromDb(id: Int) {
        repository.getMarvelCharacterDetailsFromDbFromId(
            id

        ).collect {

            marvelDetails.value = it
        }


    }

    private suspend fun writeToDb(data: Any, id: Int) {
        withContext(Dispatchers.IO) {
            repository.addMarvelCharacterDetailsToDb(
                CharacterDetail.fromBaseClasstoDto(
                    data,
                    id
                )
            )
        }

    }

    suspend fun getComicsFromCharacter(id: Int) {

        repository.getCharacterComics(id).collect {
            isComicLoading.value = true
            when (it) {
                is ResponseResult.Success -> {
                    writeToDb(it.data, id)

                    isComicLoading.value = false
                }
                is ResponseResult.Progress -> {

                    isComicLoading.value = it.isLoading
                }
                is ResponseResult.Error -> {

                    isComicLoading.value = false
                }

            }
        }
    }

    suspend fun getSeriesFromCharacter(id: Int) {

        repository.getCharacterSeries(id).collect {
            isSeriesLoading.value = true
            when (it) {
                is ResponseResult.Success -> {


                    writeToDb(it.data, id)

                    isSeriesLoading.value = false
                }
                is ResponseResult.Progress -> {
                    isSeriesLoading.value = it.isLoading
                }
                is ResponseResult.Error -> {
                    isSeriesLoading.value = false
                }

            }
        }
    }

    suspend fun getEventsFromCharacter(id: Int) {

        repository.getCharacterEvents(id).collect {
            isEventLoading.value = true
            when (it) {
                is ResponseResult.Success -> {
                    writeToDb(it.data, id)
                    isEventLoading.value = false
                }
                is ResponseResult.Progress -> {
                    isEventLoading.value = it.isLoading
                }
                is ResponseResult.Error -> {
                    isEventLoading.value = false
                }

            }
        }
    }

    suspend fun getStoriesFromCharacter(id: Int) {

        repository.getCharacterStories(id).collect {
            isStoriesLoading.value = true
            when (it) {
                is ResponseResult.Success -> {
                    writeToDb(it.data, id)
                    isStoriesLoading.value = false
                }
                is ResponseResult.Progress -> {
                    isStoriesLoading.value = it.isLoading
                }
                is ResponseResult.Error -> {
                    isStoriesLoading.value = false
                }

            }
        }
    }
}
