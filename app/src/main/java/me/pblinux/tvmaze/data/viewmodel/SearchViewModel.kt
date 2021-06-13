package me.pblinux.tvmaze.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.pblinux.tvmaze.data.models.State
import me.pblinux.tvmaze.data.repository.TVMazeRepository
import me.pblinux.tvmaze.data.source.remote.TVMazeSource
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val source: TVMazeSource,
    private val repository: TVMazeRepository,
) : ViewModel() {

    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _search: MutableStateFlow<State> = MutableStateFlow(State.Uninitialized)
    val results = _search.asStateFlow()

    private val _suggested: MutableStateFlow<State> = MutableStateFlow(State.Uninitialized)
    val suggested = _suggested.asStateFlow()

    init {
        getSuggested()
    }

    private fun getSuggested() {
        viewModelScope.launch {
            repository.getSuggestedShows().collect { _suggested.emit(it) }
        }
    }

    fun updateQuery(query: String) {
        viewModelScope.launch {
            _query.emit(query)
        }
    }

    fun search() {
        viewModelScope.launch {
            repository.search(_query.value).collect { _search.emit(it) }
        }
    }
}