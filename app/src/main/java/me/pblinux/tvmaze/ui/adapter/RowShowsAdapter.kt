package me.pblinux.tvmaze.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.databinding.RowItemBinding

class RowShowsAdapter(private val shows: List<Show>) :
    RecyclerView.Adapter<RowShowsAdapter.ViewHolder>() {

    private val currentShows: MutableList<Show> = shows.toMutableList()

    inner class ViewHolder(private val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Show) = with(binding) {
            showRow = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentShows[position])
    }

    override fun getItemCount(): Int {
        return currentShows.size
    }

    fun addItems(shows: List<Show>) {
        currentShows.addAll(shows)
        notifyDataSetChanged()
    }
}