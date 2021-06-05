package me.pblinux.tvmaze.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.pblinux.tvmaze.data.models.State
import me.pblinux.tvmaze.data.source.TVMazeSource
import javax.inject.Inject
import kotlin.random.Random

class TVMazeRepository @Inject constructor(private val remoteSource: TVMazeSource) {
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