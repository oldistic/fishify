package com.dicoding.fishify.ui.regis.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.fishify.R
import com.dicoding.fishify.databinding.ActivityLoginBinding
import com.dicoding.fishify.model.TokenModel
import com.dicoding.fishify.ui.ViewModelFactory
import com.dicoding.fishify.ui.main.MainActivity


class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels { viewModelFactory }
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupAction()
        playAnimation()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(this)
    }

    private fun showLoading() {
        loginViewModel.showLoading.observe(this@LoginActivity) {
            binding.progressBarLogin.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun showToast() {
        loginViewModel.toastText.observe(this@LoginActivity) { toastText ->
            Toast.makeText(
                this@LoginActivity, toastText, Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun postText() {
        binding.apply {
            loginViewModel.loginAccount(
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }

        loginViewModel.loginResult.observe(this@LoginActivity) { response ->
            if (response != null) {
                saveState(
                    TokenModel(
                        response.name,
                        AUTH_KEY + (response.token),
                        true
                    )
                )
            }
        }
    }

    private fun saveState(session: TokenModel){
        loginViewModel.saveState(session)
    }

    companion object {
        private const val AUTH_KEY = "Bearer "
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            when {
                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = getString(R.string.input_email)
                }
                password.isEmpty() -> {
                    binding.passwordEditTextLayout.error = getString(R.string.input_password)
                }
                email != email -> {
                    binding.emailEditTextLayout.error = getString(R.string.email_dont_match)
                }
                password != password -> {
                    binding.passwordEditTextLayout.error = getString(R.string.password_dont_match)
                }
                else -> {
                    loginViewModel.login()
                    showLoading()
                    postText()
                    showToast()
                    intentActivity()
                }
            }
        }
    }

    private fun intentActivity() {
        loginViewModel.loginResponse.observe(this@LoginActivity) { response ->
            if (!response.error) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val message =
            ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(100)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                title,
                message,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login
            )
            startDelay = 100
        }.start()
    }
}