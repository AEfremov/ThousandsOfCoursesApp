package ru.keepitlock.featureaccount.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.keepitlock.coredi.ComponentHolder
import ru.keepitlock.coredi.ViewModelFactory
import ru.keepitlock.coreui.BaseFragment
import ru.keepitlock.featureaccount.databinding.FragmentAccountBinding
import ru.keepitlock.featureaccount.di.AccountDependencies
import ru.keepitlock.featureaccount.di.DaggerAccountComponent
import javax.inject.Inject

class AccountFragment : BaseFragment<FragmentAccountBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AccountViewModel by viewModels { viewModelFactory }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAccountBinding {
        return FragmentAccountBinding.inflate(inflater, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

    private fun injectDependencies() {
        val appComponent = (requireActivity().application as ComponentHolder<AccountDependencies>).getComponent()
        DaggerAccountComponent.factory()
            .create(appComponent)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}