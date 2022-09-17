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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {
    private var getCharacterDetailsJob: Job? = null
    var isComicLoading = mutableStateOf(false)
    var isEventLoading = mutableStateOf(false)
    var isSeriesLoading = mutableStateOf(false)
    var isStoriesLoading = mutableStateOf(false)
    var eventLoadError = mutableStateOf("")
    var comicLoadError = mutableStateOf("")
    var storiesLoadError = mutableStateOf("")
    var seriesLoadError = mutableStateOf("")
    val loadState: MutableLiveData<Boolean> = MutableLiveData(false)
    var comicsList = mutableStateOf<ArrayList<CharacterDetail>>(arrayListOf())
    var eventList = mutableStateOf<ArrayList<CharacterDetail>>(arrayListOf())
    var storiesList = mutableStateOf<ArrayList<CharacterDetail>>(arrayListOf())
    var seriesList = mutableStateOf<ArrayList<CharacterDetail>>(arrayListOf())

    fun getCharacterDetails(id: Int) {
        getCharacterDetailsJob.cancelIfActive()
        getCharacterDetailsJob = viewModelScope.launch {
            loadState.value = true
            launch { getComicsFromCharacter(id) }
            launch { getSeriesFromCharacter(id) }
            launch { getEventsFromCharacter(id) }
            launch { getStoriesFromCharacter(id) }

        }


    }

     suspend fun getComicsFromCharacter(id: Int) {

        repository.getCharacterComics(id).collect {
            isComicLoading.value = true
            when (it) {
                is ResponseResult.Success -> {
                    comicsList.value = CharacterDetail.fromBaseClasstoDto(it.data)

                    // println("this is data" + it.data.toString())
                    // comicData.value = it.data
                    isComicLoading.value = false
                }
                is ResponseResult.Progress -> {
                    println("this is data" + "loading")

                    isComicLoading.value = it.isLoading
                }
                is ResponseResult.Error -> {
                    println("this is data" + it.errmessage)

                    isComicLoading.value = false
                    comicLoadError.value = it.errmessage
                }

            }
        }
    }

     suspend fun getSeriesFromCharacter(id: Int) {

        repository.getCharacterSeries(id).collect {
            isSeriesLoading.value = true
            when (it) {
                is ResponseResult.Success -> {
                    seriesList.value = CharacterDetail.fromBaseClasstoDto(it.data)
                  //  seriesData.value = it.data
                    isSeriesLoading.value = false
                }
                is ResponseResult.Progress -> {
                    isSeriesLoading.value = it.isLoading
                }
                is ResponseResult.Error -> {
                    isSeriesLoading.value = false
                    seriesLoadError.value = it.errmessage
                }

            }
        }
    }

     suspend fun getEventsFromCharacter(id: Int) {

        repository.getCharacterEvents(id).collect {
            isEventLoading.value = true
            when (it) {
                is ResponseResult.Success -> {
                    eventList.value = CharacterDetail.fromBaseClasstoDto(it.data)

                    //eventData.value = it.data
                    isEventLoading.value = false
                }
                is ResponseResult.Progress -> {
                    isEventLoading.value = it.isLoading
                }
                is ResponseResult.Error -> {
                    isEventLoading.value = false
                    eventLoadError.value = it.errmessage
                }

            }
        }
    }

     suspend fun getStoriesFromCharacter(id: Int) {

        repository.getCharacterStories(id).collect {
            isStoriesLoading.value = true
            when (it) {
                is ResponseResult.Success -> {
                    storiesList.value = CharacterDetail.fromBaseClasstoDto(it.data)

                    // storiesData.value = it.data
                    isStoriesLoading.value = false
                }
                is ResponseResult.Progress -> {
                    isStoriesLoading.value = it.isLoading
                }
                is ResponseResult.Error -> {
                    isStoriesLoading.value = false
                    storiesLoadError.value = it.errmessage
                }

            }
        }
    }
}
