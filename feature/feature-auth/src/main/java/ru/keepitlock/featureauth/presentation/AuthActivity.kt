package ru.keepitlock.featureauth.presentation

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
import ru.keepitlock.featureauth.di.DaggerAuthComponent
import javax.inject.Inject

private const val URL_VK = "https://vk.com/"
private const val URL_OK = "https://ok.ru/"

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
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
            buttonVk.setOnClickListener {
                openUrl(URL_VK)
            }
            buttonOk.setOnClickListener {
                openUrl(URL_OK)
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
                                navigateToMain()
                            }
                            null -> {}
                        }
                    }
                }
            }
        }
    }

    private fun navigateToMain() {
        authNavigator.navigateToMain()
        viewModel.onLoginEventHandled()
        finish()
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }
}