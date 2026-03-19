package ru.keepitlock.featurecourses.presentation

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
import ru.keepitlock.coreui.gone
import ru.keepitlock.coreui.visible
import ru.keepitlock.featurecourses.databinding.FragmentCoursesBinding
import ru.keepitlock.featurecourses.di.CoursesDependencies
import ru.keepitlock.featurecourses.di.DaggerCoursesComponent
import ru.keepitlock.featurecourses.presentation.adapter.CoursesAdapter
import javax.inject.Inject

class CoursesFragment : BaseFragment<FragmentCoursesBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CoursesViewModel by viewModels { viewModelFactory }

    private val coursesAdapter = CoursesAdapter { course ->
        // Handle course click
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCoursesBinding {
        return FragmentCoursesBinding.inflate(inflater, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

    private fun injectDependencies() {
        val appComponent = (requireActivity().application as ComponentHolder<CoursesDependencies>).getComponent()
        DaggerCoursesComponent.factory()
            .create(appComponent)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.coursesRecyclerView.apply {
            adapter = coursesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    when (state) {
                        is CoursesUiState.Initial -> {
                            // Initial state
                        }
                        is CoursesUiState.Loading -> {
                            binding.progressBar.visible()
                            binding.coursesRecyclerView.gone()
                            binding.errorTextView.gone()
                        }
                        is CoursesUiState.Success -> {
                            binding.progressBar.gone()
                            binding.coursesRecyclerView.visible()
                            binding.errorTextView.gone()
                            coursesAdapter.items = state.courses
                        }
                        is CoursesUiState.Error -> {
                            binding.progressBar.gone()
                            binding.coursesRecyclerView.gone()
                            binding.errorTextView.visible()
                            binding.errorTextView.text = state.message
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = CoursesFragment()
    }
}