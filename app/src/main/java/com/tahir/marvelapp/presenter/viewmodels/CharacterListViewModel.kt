package com.tahir.marvelapp.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.tahir.marvelapp.constants.WebServiceConstants
import com.tahir.marvelapp.data.db.AppDatabase
import com.tahir.marvelapp.data.paging.MarvelRemoteMediator
import com.tahir.marvelapp.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@HiltViewModel
class CharacterListViewModel @Inject constructor(private val repository: Repository,appDb:AppDatabase) :
    ViewModel() {
    /**
     * used as collectAsLazyPagingItems in the UI
     * @see CharacterList.kt
     */
    @OptIn(ExperimentalPagingApi::class)
    val characterPager = Pager(PagingConfig(pageSize = WebServiceConstants.PAGE_SIZE), remoteMediator =MarvelRemoteMediator(repository,appDb) ) {
       repository.getCharactersFromDb()
    }.flow.cachedIn(viewModelScope)
}
