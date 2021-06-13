package me.pblinux.tvmaze.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.databinding.GridItemBinding
import me.pblinux.tvmaze.databinding.RowItemBinding

enum class SearchItemType(val num: Int) {
    ROW(1), GRID(2)
}

class SearchShowsAdapter(shows: List<Show>, type: SearchItemType) :
    RecyclerView.Adapter<SearchShowsAdapter.ViewHolder>() {

    private var currentType = type
    private var currentShows = shows

    inner class ViewHolder : RecyclerView.ViewHolder {
        private var rowItemBinding: RowItemBinding? = null
        private var gridItemBinding: GridItemBinding? = null

        constructor(binding: RowItemBinding) : super(binding.root) {
            rowItemBinding = binding
        }

        constructor(binding: GridItemBinding) : super(binding.root) {
            gridItemBinding = binding
        }

        fun bindRow(show: Show) = with(rowItemBinding) { this?.let { showRow = show } }
        fun bindGrid(show: Show) = with(gridItemBinding) { this?.let { showItem = show } }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == SearchItemType.ROW.num) {
            ViewHolder(
                RowItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        } else {
            ViewHolder(
                GridItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemViewType == SearchItemType.ROW.num) {
            holder.bindRow(currentShows[position])
        } else {
            holder.bindGrid(currentShows[position])
        }
    }

    override fun getItemCount(): Int {
        return currentShows.size
    }

    override fun getItemViewType(position: Int): Int {
        return currentType.num
    }

    fun changeData(shows: List<Show>, type: SearchItemType) {
        currentType = type
        currentShows = shows
        this.notifyDataSetChanged()
    }
}