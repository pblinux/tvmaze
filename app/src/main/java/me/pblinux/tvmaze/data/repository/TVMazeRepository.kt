package me.pblinux.tvmaze.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import me.pblinux.tvmaze.data.models.State
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.data.source.local.TVDatabase
import me.pblinux.tvmaze.data.source.remote.TVMazeSource
import javax.inject.Inject
import kotlin.random.Random

class TVMazeRepository @Inject constructor(
    private val remoteSource: TVMazeSource,
    private val database: TVDatabase
) {
    // Database operations
    fun getFavourites(): Flow<List<Show>> {
        return database.showDao().getAll()
            .distinctUntilChanged()
            .map { it.sortedBy { show -> show.name } }
            .flowOn(Dispatchers.IO)
    }

    suspend fun addToFavourites(show: Show) {
        val dao = database.showDao()
        dao.add(show)
    }

    suspend fun removeFromFavourites(show: Show) {
        val dao = database.showDao()
        dao.remove(show)
    }

    // Remote operations
    suspend fun getEpisodes(showId: Int): Flow<State> {
        return flow {
            emit(State.Loading)
            val result = remoteSource.getEpisodes(showId)
            if (result.isSuccess) {
                val grouped = result.getOrDefault(listOf()).groupBy { it.season }
                emit(State.Loaded(grouped))
            } else {
                emit(State.Error)
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSuggestedShows(): Flow<State> {
        return flow {
            emit(State.Loading)
            val result = remoteSource.getShows(Random.nextInt(5, 50))
            val shows = result.getOrDefault(listOf()).take(6)
            if (result.isSuccess) {
                emit(State.Loaded(shows))
            } else {
                emit(State.Error)
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun search(query: String): Flow<State> {
        return flow {
            emit(State.Loading)
            val result = remoteSource.search(query = query)
            if (result.isSuccess) {
                emit(State.Loaded(result.getOrDefault(listOf())))
            } else {
                emit(State.Error)
            }
        }.flowOn(Dispatchers.IO)
    }
}