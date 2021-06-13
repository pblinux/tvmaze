package me.pblinux.tvmaze.data.source.remote

import me.pblinux.tvmaze.data.models.episode.Episode
import me.pblinux.tvmaze.data.models.search.SearchResult
import me.pblinux.tvmaze.data.models.show.Show
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVMazeService {
    /*
    Gets all show available.
    * */
    @GET("/shows")
    suspend fun getShows(@Query("page") page: Int): Response<List<Show>>

    /*
    Search a show based on query.
    * */
    @GET("/search/shows")
    suspend fun search(@Query("q") query: String): Response<List<SearchResult>>

    /*
    Get a show complete info
    * */
    @GET("/shows/{id}/episodes")
    suspend fun getEpisodes(@Path("id") id: Int): Response<List<Episode>>
}