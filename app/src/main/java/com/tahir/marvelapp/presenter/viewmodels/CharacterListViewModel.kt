package com.tahir.marvelapp.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.tahir.marvelapp.constants.WebServiceConstants
import com.tahir.marvelapp.data.paging.MarvelCharactersDataSource
import com.tahir.marvelapp.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CharacterListViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {
    /**
     * used as collectAsLazyPagingItems in the UI
     * @see CharacterList.kt
     */
    val characterPager = Pager(PagingConfig(pageSize = WebServiceConstants.PAGE_SIZE)) {
        MarvelCharactersDataSource(repository)
    }.flow.cachedIn(viewModelScope)
}
