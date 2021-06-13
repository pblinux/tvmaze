package me.pblinux.tvmaze.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import me.pblinux.tvmaze.data.viewmodel.HomeViewModel
import me.pblinux.tvmaze.databinding.FragmentShowsBinding
import me.pblinux.tvmaze.ui.adapter.PagedShowsAdapter


@AndroidEntryPoint
class ShowsFragment : Fragment() {
    // Binding
    private var _binding: FragmentShowsBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    private val homeViewModel: HomeViewModel by activityViewModels()

    // Adapter
    private val showsAdapter = PagedShowsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.showsList.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = showsAdapter
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            homeViewModel.shows.collectLatest { data ->
                showsAdapter.submitData(data)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ShowsFragment().apply {}
    }
}