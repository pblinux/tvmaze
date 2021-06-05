package me.pblinux.tvmaze.data.source

import android.util.Log
import me.pblinux.tvmaze.data.models.episode.Episode
import me.pblinux.tvmaze.data.models.search.SearchResult
import me.pblinux.tvmaze.data.models.show.Show
import retrofit2.Response
import javax.inject.Inject

class TVMazeSource @Inject constructor(private val service: TVMazeService) {
    suspend fun getShows(page: Int): Result<List<Show>> {
        return response { service.getShows(page) }
    }

    suspend fun getEpisodes(showId: Int): Result<List<Episode>> {
        return response { service.getEpisodes(showId) }
    }

    suspend fun search(query: String): Result<List<SearchResult>> {
        return response { service.search(query = query) }
    }

    private suspend fun <T> response(
        request: suspend () -> Response<T>
    ): Result<T> {
        return try {
            val result = request.invoke()
            Result.success(result.body()!!)
        } catch (e: Throwable) {
            Log.e("TVMaze", e.message.toString())
            Result.failure(e)
        }
    }

}