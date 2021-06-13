package me.pblinux.tvmaze.ui.binding

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textview.MaterialTextView
import me.pblinux.tvmaze.data.models.episode.Episode
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.databinding.ChipItemBinding
import me.pblinux.tvmaze.databinding.EpisodeItemBinding
import me.pblinux.tvmaze.databinding.TextItemBinding
import me.pblinux.tvmaze.ui.activity.ShowActivity


@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    this.load(url) {
        crossfade(true)
    }
}

@BindingAdapter("onShowClicked")
fun View.onShowClick(show: Show) {
    this.setOnClickListener {
        this.context.startActivity(
            Intent(this.context, ShowActivity::class.java).apply {
                putExtra("show", show)
            }
        )
    }
}

@BindingAdapter("onEpisodeClicked")
fun View.onEpisodeClicked(episode: Episode) {
    this.setOnClickListener {
//        this.context.startActivity(
//            Intent(this.context, ShowActivity::class.java).apply {
//                putExtra("show", show)
//            }
//        )
    }
}

@BindingAdapter("items")
fun FlexboxLayout.items(items: List<String>) {
    val inflater: LayoutInflater =
        this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    items.forEach {
        val binding = ChipItemBinding.inflate(inflater, this, false)
        binding.chipText = it
        this.addView(binding.root)
    }
}

@BindingAdapter("items", "time")
fun LinearLayoutCompat.items(items: List<String>, time: String) {
    val inflater: LayoutInflater =
        this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    items.forEach {
        val binding = TextItemBinding.inflate(inflater, this, false)
        binding.text = "$it at $time"
        this.addView(binding.root)
    }
}

@BindingAdapter("episodes")
fun LinearLayoutCompat.episodes(items: List<Episode>) {
    val inflater: LayoutInflater =
        this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    this.removeAllViewsInLayout()
    items.forEach {
        val binding = EpisodeItemBinding.inflate(inflater, this, false)
        binding.episode = it
        this.addView(binding.root)
    }
}

@BindingAdapter("fixedText")
fun MaterialTextView.fixedText(text: String) {
    this.text = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString();
    } else {
        Html.fromHtml(text).toString()
    }.trim()
}

@BindingAdapter("items")
fun TabLayout.items(items: List<String>) {
    items.forEach {
        this.addTab(TabLayout(this.context).newTab().apply { text = it })
    }
}