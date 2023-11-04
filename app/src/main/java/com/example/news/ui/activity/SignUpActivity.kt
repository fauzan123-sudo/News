package com.example.news.ui.activity

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.news.data.model.auth.RequestRegister
import com.example.news.databinding.ActivitySignUpBinding
import com.example.news.ui.viewmodel.MyAuthViewModel
import com.example.news.util.Resource
import com.example.news.util.toast
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {

    private val TAG = "SignUpActivity"
    private val viewModel: MyAuthViewModel by viewModels()
    override fun onViewCreated(binding: ActivitySignUpBinding) {

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            if (username.isEmpty()) {
                toast("username must be filled in")
            } else if (name.isEmpty()) {
                toast("name must be filled in")
            } else if (password.isEmpty()) {
                toast("password must be filled in")
            } else if (confirmPassword.isEmpty()) {
                toast("confirm password must be filled in")
            } else if (password != confirmPassword) {
                toast("password do not match")
            } else {
                val userId = Random.nextInt(1, 101)
                viewModel.userRegistration(
                    RequestRegister(
                        name = name,
                        password = password,
                        username = username,
                        role = "2"
                    )
                )
                viewModel.userRegistrationResponse.observe(this) {
                    when (it) {
                        is Resource.Success -> {
                            startActivity(Intent(this, LoginActivity::class.java))
                        }

                        is Resource.Loading -> {
                            binding.progressCircular.isVisible = true
                        }

                        is Resource.Error -> {
                            toast(it.message ?: "something with wrong")
                            Log.e(TAG, it.message ?: "error occur")
                        }

                        else -> {
                            Log.d(TAG, "Something ")
                        }
                    }
                }
//                lifecycleScope.launch {
//                    viewModel.signUpFlow.collect { handleSignUpResult(it) }
//                }
            }
        }
    }

    private fun handleSignUpResult(it: Resource<FirebaseUser>?) {
        binding.progressCircular.isVisible = false
        when (it) {
            is Resource.Success -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }

            is Resource.Loading -> {
                binding.progressCircular.isVisible = true
            }

            is Resource.Error -> {
                toast(it.message ?: "something with wrong")
                Log.e(TAG, it.message ?: "error occur")
            }

            else -> {
                Log.d(TAG, "Something ")
            }
        }
    }
}