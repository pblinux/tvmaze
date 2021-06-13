package me.pblinux.tvmaze.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.databinding.GridItemBinding

class ShowsAdapter(private val shows: List<Show>) :
    RecyclerView.Adapter<ShowsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: GridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Show) = with(binding) {
            showItem = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            GridItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount(): Int {
        return shows.size
    }
}