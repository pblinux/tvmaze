package me.pblinux.tvmaze.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.pblinux.tvmaze.data.models.State
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.data.paging.PagingShows
import me.pblinux.tvmaze.data.repository.TVMazeRepository
import me.pblinux.tvmaze.data.source.remote.TVMazeSource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val source: TVMazeSource,
    private val repository: TVMazeRepository
) : ViewModel() {
    var shows: Flow<PagingData<Show>> =
        Pager(PagingConfig(pageSize = 10)) { PagingShows(source) }.flow.cachedIn(viewModelScope)

    private val _favourites: MutableStateFlow<List<Show>> = MutableStateFlow(listOf())
    val favourites: StateFlow<List<Show>> = _favourites.asStateFlow()

    init {
        getFavourites()
    }

    private fun getFavourites() {
        viewModelScope.launch {
            repository.getFavourites().collect { _favourites.emit(it) }
        }
    }

    fun addToFavourites(show: Show) {
        viewModelScope.launch {
            repository.addToFavourites(show)
        }
    }

    fun removeFromFavourites(show: Show) {
        viewModelScope.launch {
            repository.removeFromFavourites(show)
        }
    }
}