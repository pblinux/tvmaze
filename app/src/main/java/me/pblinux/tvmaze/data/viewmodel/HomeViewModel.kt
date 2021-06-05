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
import me.pblinux.tvmaze.data.source.TVMazeSource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val source: TVMazeSource,
    private val repository: TVMazeRepository
) : ViewModel() {
    var shows: Flow<PagingData<Show>> =
        Pager(PagingConfig(pageSize = 10)) { PagingShows(source) }.flow.cachedIn(viewModelScope)

    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _search: MutableStateFlow<State> = MutableStateFlow(State.Uninitialized)
    val results = _search.asStateFlow()

    private val _suggested: MutableStateFlow<State> = MutableStateFlow(State.Uninitialized)
    val suggested = _suggested.asStateFlow()

    init {
        getSuggested()
    }

    fun updateQuery(query: String) {
        viewModelScope.launch {
            _query.emit(query)
        }
    }

    private fun getSuggested() {
        viewModelScope.launch {
            repository.getSuggestedShows().collect { _suggested.emit(it) }
        }
    }

    fun search() {
        viewModelScope.launch {
            repository.search(_query.value).collect { _search.emit(it) }
        }
    }
}