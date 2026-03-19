package ru.keepitlock.featureauth.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.keepitlock.coredi.ComponentHolder
import ru.keepitlock.coredi.ViewModelFactory
import ru.keepitlock.coreui.BaseActivity
import ru.keepitlock.featureauth.databinding.ActivityAuthBinding
import ru.keepitlock.featureauth.di.AuthDependencies
import javax.inject.Inject
import androidx.core.net.toUri
import ru.keepitlock.featureauth.di.DaggerAuthComponent

class AuthActivity : BaseActivity<ActivityAuthBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var authNavigator: AuthNavigator

    private val viewModel: AuthViewModel by viewModels { viewModelFactory }

    override fun createBinding(inflater: LayoutInflater): ActivityAuthBinding {
        return ActivityAuthBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun injectDependencies() {
        val appComponent = (application as ComponentHolder<AuthDependencies>).getComponent()
        DaggerAuthComponent.factory()
            .create(appComponent)
            .inject(this)
    }

    private fun setupViews() {
        with(binding) {
            val cyrillicFilter = InputFilter { source, _, _, _, _, _ ->
                source.filter { it !in 'а'..'я' && it !in 'А'..'Я' }
            }
            emailEditText.filters = arrayOf(cyrillicFilter)
            emailEditText.doAfterTextChanged { text ->
                viewModel.onEmailChanged(text?.toString() ?: "")
            }
            passwordEditText.doAfterTextChanged { text ->
                viewModel.onPasswordChanged(text?.toString() ?: "")
            }
            loginButton.setOnClickListener {
                viewModel.onLoginClicked()
            }
            registerTextView.isEnabled = false
            forgotPasswordTextView.isEnabled = false
            vkButton.setOnClickListener {
                openUrl("https://vk.com/")
            }
            okButton.setOnClickListener {
                openUrl("https://ok.ru/")
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.isLoginEnabled.collectLatest { isEnabled ->
                        binding.loginButton.isEnabled = isEnabled
                    }
                }
                launch {
                    viewModel.loginEvent.collectLatest { event ->
                        when (event) {
                            is AuthViewModel.LoginEvent.NavigateToMain -> {
//                                navigateToMain()
                                authNavigator.navigateToMain()
                                viewModel.onLoginEventHandled()
                                finish()
                            }
                            null -> {}
                        }
                    }
                }
            }
        }
    }

//    private fun navigateToMain() {
////        val intent = Intent(this, MainActivity::class.java)
////        startActivity(intent)
////        finish()
//    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }
}