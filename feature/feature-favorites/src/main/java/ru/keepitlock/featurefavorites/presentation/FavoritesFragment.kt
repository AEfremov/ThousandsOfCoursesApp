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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.keepitlock.coredi.ComponentHolder
import ru.keepitlock.coredi.ViewModelFactory
import ru.keepitlock.coreui.BaseFragment
import ru.keepitlock.featurefavorites.databinding.FragmentFavoritesBinding
import ru.keepitlock.featurefavorites.di.DaggerFavoritesComponent
import ru.keepitlock.featurefavorites.di.FavoritesDependencies
import javax.inject.Inject

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FavoritesViewModel by viewModels { viewModelFactory }

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
        observeViewModel()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.uiState.collectLatest { state ->
//                    // Handle UI state
//                }
//            }
        }
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}