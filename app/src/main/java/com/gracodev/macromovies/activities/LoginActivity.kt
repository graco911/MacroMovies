package com.gracodev.macromovies.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gracodev.macromovies.R
import com.gracodev.macromovies.databinding.ActivityLoginBinding
import com.gracodev.macromovies.states.UIStates
import com.gracodev.macromovies.utils.alert
import com.gracodev.macromovies.utils.neutralButton
import com.gracodev.macromovies.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            loginButton.setOnClickListener {
                viewModel.setEmail(editTextTextEmailAddress.text.toString())
                viewModel.setPass(editTextTextPassword.text.toString())
                viewModel.login()
            }
        }

        initViewModel()
    }

    private fun initViewModel() {

        viewModel.loginValidate.observe(this) { uiStates ->
            when (uiStates) {
                is UIStates.Error -> {

                    alert {
                        setTitle(getString(R.string.main_activity_dialog_title_text))
                        setMessage(
                            getString(R.string.main_activity_error_message_text)
                        )
                        neutralButton(getString(R.string.main_activity_dialog_accept_button_text)) {
                            it.dismiss()
                        }
                    }
                }

                UIStates.Init -> {
                }

                UIStates.Loading -> {
                }

                is UIStates.Success -> {
                    viewModel.performLogin()
                }
            }
        }

        viewModel.loginResult.observe(this) { uiState ->
            when (uiState) {
                is UIStates.Error -> {

                    alert {
                        setTitle(getString(R.string.main_activity_dialog_title_text))
                        setMessage(
                            getString(R.string.main_activity_error_message_text)
                        )
                        neutralButton(getString(R.string.main_activity_dialog_accept_button_text)) {
                            it.dismiss()
                        }
                    }
                }

                UIStates.Init -> {
                }

                UIStates.Loading -> {
                }

                is UIStates.Success -> {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}