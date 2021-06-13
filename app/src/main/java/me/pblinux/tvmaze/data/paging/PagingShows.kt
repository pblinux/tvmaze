package me.pblinux.tvmaze.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.data.source.remote.TVMazeSource

class PagingShows(
    private val source: TVMazeSource,
) : PagingSource<Int, Show>() {
    override fun getRefreshKey(state: PagingState<Int, Show>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
        return try {
            val next = params.key ?: 1
            val shows = source.getShows(page = next).getOrDefault(listOf())
            LoadResult.Page(
                data = shows,
                prevKey = null,
                nextKey = if (shows.isNotEmpty()) next.plus(1) else null
            )
        } catch(e: Error) {
            LoadResult.Error(e)
        }
    }
}