package me.pblinux.tvmaze.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.databinding.GridItemBinding

class PagedShowsAdapter() :
    PagingDataAdapter<Show, PagedShowsAdapter.PagedShowsViewHolder>(ShowComparator) {

    inner class PagedShowsViewHolder(private val binding: GridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Show) = with(binding) {
            Log.d("TEST", item.name)
            showItem = item
        }
    }

    object ShowComparator : DiffUtil.ItemCallback<Show>() {
        override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean =
            oldItem == newItem
    }

    override fun onBindViewHolder(holder: PagedShowsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagedShowsViewHolder =
        PagedShowsViewHolder(
            GridItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}