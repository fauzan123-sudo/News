package com.example.news.ui.activity

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.news.data.model.auth.User
import com.example.news.databinding.ActivityLoginBinding
import com.example.news.ui.viewmodel.MyAuthViewModel
import com.example.news.util.Resource
import com.example.news.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val viewModel: MyAuthViewModel by viewModels()
    private val TAG = "LoginActivity"

    override fun onViewCreated(binding: ActivityLoginBinding) {

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }


        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isEmpty()) {
                Toast.makeText(this, "username must be filled", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "password must be filled", Toast.LENGTH_SHORT).show()
            } else if (username.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "password must be filled", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.requestLogin2(username, password)
                lifecycleScope.launch {
                    viewModel.userLoginResponse.collect { handleLoginResult(it) }
                }
            }
        }
    }

    private fun handleLoginResult(result: Resource<User>?) {
        binding.progressCircular.isVisible = false
        when (result) {
            is Resource.Success -> {
                val response = result.data!!
                if (response != null) {
                    toast(response.username)
                } else {
                    toast("failure login")
                }
//                startActivity(Intent(this, MainActivity::class.java))
            }

            is Resource.Loading -> {
                binding.progressCircular.isVisible = true
            }

            is Resource.Error -> {
                Log.e(TAG, result.message ?: "")
                Toast.makeText(this, "${result.message}", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Log.d(TAG, "handleLoginResult: ")
            }
        }
    }


}