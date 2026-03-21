package ru.keepitlock.featurefavorites.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.keepitlock.coredi.ComponentHolder
import ru.keepitlock.coredi.ViewModelFactory
import ru.keepitlock.coreui.BaseFragment
import ru.keepitlock.coreui.ext.gone
import ru.keepitlock.coreui.ext.visible
import ru.keepitlock.featurefavorites.databinding.FragmentFavoritesBinding
import ru.keepitlock.featurefavorites.di.DaggerFavoritesComponent
import ru.keepitlock.featurefavorites.di.FavoritesDependencies
import ru.keepitlock.featurefavorites.presentation.adapter.FavoritesAdapter
import javax.inject.Inject

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FavoritesViewModel by viewModels { viewModelFactory }

    private val favoritesAdapter = FavoritesAdapter(
        onItemClick = { course ->
            println("Course clicked: ${course.title}")
            // TODO: Navigate to course details
        },
        onBookmarkClick = { course ->
            viewModel.toggleFavorite(course.id)
        }
    )

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoritesBinding {
        return FragmentFavoritesBinding.inflate(inflater, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

    private fun injectDependencies() {
        val appComponent = (requireActivity().application as ComponentHolder<FavoritesDependencies>).getComponent()
        DaggerFavoritesComponent.factory()
            .create(appComponent)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewFavorites.apply {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    when (state) {
                        is FavoritesUiState.Initial -> {
                            // Initial state - nothing to show
                        }
                        is FavoritesUiState.Loading -> {
                            binding.progressBar.visible()
                            binding.recyclerViewFavorites.gone()
                            binding.emptyTextView.gone()
                        }
                        is FavoritesUiState.Success -> {
                            binding.progressBar.gone()
                            binding.recyclerViewFavorites.visible()

                            if (state.courses.isEmpty()) {
                                binding.recyclerViewFavorites.gone()
                                binding.emptyTextView.visible()
                            } else {
                                binding.emptyTextView.gone()
                                favoritesAdapter.items = state.courses
                            }
                        }
                        is FavoritesUiState.Error -> {
                            binding.progressBar.gone()
                            binding.recyclerViewFavorites.gone()
                            binding.emptyTextView.gone()
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}