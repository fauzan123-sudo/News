package com.example.news.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.model.auth.RequestRegister
import com.example.news.data.model.auth.User
import com.example.news.data.repository.MyAuthRepository
import com.example.news.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAuthViewModel @Inject constructor(
    private val repository: MyAuthRepository
) : ViewModel() {

    private val _userRegistrationResponse = MutableLiveData<Resource<Unit>>()
    val userRegistrationResponse: LiveData<Resource<Unit>> = _userRegistrationResponse

    private val _userLoginResponse: MutableStateFlow<Resource<User>> =
        MutableStateFlow(Resource.Loading())
    val userLoginResponse: StateFlow<Resource<User>> = _userLoginResponse

    fun userRegistration(user: RequestRegister) {
        viewModelScope.launch {
            val response = repository.userRegistration(user)
            _userRegistrationResponse.value = response
        }
    }

    fun requestLogin2(username:String, password:String) {
        viewModelScope.launch {
            repository.userLogin(username, password).collect { result ->
                _userLoginResponse.value = result
            }
        }
    }

    fun requestLogin(username:String, password:String) {
        viewModelScope.launch {
            repository.userLogin(username, password).collect { result ->
                _userLoginResponse.value = result
            }
        }
    }

    fun requestLogin3(username: String, password: String): Flow<Resource<User>> {
        return repository.login3(username, password)
    }





//    fun login(email: String, password: String) = viewModelScope.launch {
//        _loginFlow.value = Resource.Loading()
//        val result = repository.login(email, password)
//        _loginFlow.value = result
//    }
//
//    fun signup(name: String, email: String, password: String) = viewModelScope.launch {
//        _signupFlow.value = Resource.Loading()
//        val result = repository.signup(name, email, password)
//        _signupFlow.value = result
//    }
//
//    fun logout() {
//        repository.logout()
//        _loginFlow.value = null
//        _signupFlow.value = null
//    }
}