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
import me.pblinux.tvmaze.data.models.episode.Episode
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.data.repository.TVMazeRepository
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(
    private val repository: TVMazeRepository
) : ViewModel() {
    private val _show: MutableStateFlow<Show?> = MutableStateFlow(null)
    val show: StateFlow<Show?> = _show.asStateFlow()

    private val _episode: MutableStateFlow<Episode?> = MutableStateFlow(null)
    val episode: StateFlow<Episode?> = _episode.asStateFlow()

    private val _episodes: MutableStateFlow<State> = MutableStateFlow(State.Uninitialized)
    val episodes = _episodes.asStateFlow()

    fun changeShow(show: Show) {
        viewModelScope.launch {
            _show.emit(show)
            getEpisodes()
        }
    }

    fun changeEpisode(episode: Episode) {
        viewModelScope.launch {
            _episode.emit(episode)
        }
    }

    private fun getEpisodes() {
        viewModelScope.launch {
            _show.collect { current ->
                current?.let { show ->
                    repository.getEpisodes(show.id).collect { _episodes.emit(it) }
                }
            }
        }
    }
}