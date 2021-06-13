package me.pblinux.tvmaze.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.pblinux.tvmaze.data.models.State
import me.pblinux.tvmaze.data.models.search.SearchResult
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.data.viewmodel.SearchViewModel
import me.pblinux.tvmaze.databinding.ActivitySearchBinding
import me.pblinux.tvmaze.ui.adapter.SearchItemType
import me.pblinux.tvmaze.ui.adapter.SearchShowsAdapter

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    // Binding
    private lateinit var binding: ActivitySearchBinding

    // ViewModel
    private val searchViewModel: SearchViewModel by viewModels()

    // Adapter
    private var searchAdapter: SearchShowsAdapter =
        SearchShowsAdapter(listOf(), SearchItemType.GRID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        binding.viewModel = searchViewModel
        binding.suggestionList.apply {
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = searchAdapter
        }
        setContentView(binding.root)
        setupSearch()
        setupContent()
    }

    private fun setupContent() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    Log.d("TEST", "PRUEBA 5")
                    searchViewModel.results.collect { searchState ->
                        when (searchState) {
                            is State.Loaded<*> -> {
                                val data = searchState.data as List<SearchResult>
                                Log.d("TEST", "PRUEBA 1")
                                binding.suggestionList.apply {
                                    layoutManager = LinearLayoutManager(this.context)
                                    searchAdapter.changeData(
                                        data.map { it.show },
                                        SearchItemType.ROW
                                    )
                                }
                            }
                            else -> {
                                Log.d("TEST", "PRUEBA 2")
                            }
                        }
                    }
                }
                launch {
                    searchViewModel.suggested.collectLatest { suggestedState ->
                        when (suggestedState) {
                            is State.Loaded<*> -> {
                                val data = suggestedState.data as List<*>
                                binding.suggestionList.apply {
                                    layoutManager = GridLayoutManager(this.context, 2)
                                    searchAdapter.changeData(
                                        data as List<Show>,
                                        SearchItemType.GRID
                                    )
                                }
                            }
                            else -> {
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupSearch() {
        binding.searchInput.doAfterTextChanged { searchViewModel.updateQuery(it.toString()) }
        binding.searchInput.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    searchViewModel.search()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}