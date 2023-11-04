package com.example.news.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.news.data.repository.PaintMasterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: PaintMasterRepository
) : ViewModel() {

//    private val _categoryResponse = MutableStateFlow<Resource<Unit>>(Resource.Empty)
//    val categoryResponse: StateFlow<Resource<Unit>> = _categoryResponse
//
//    fun addCategory(request: RequestCategory) = viewModelScope.launch {
//        _categoryResponse.value = Resource.Loading()
//        val result = repository.addCategory(
//            request
//        )
//        _categoryResponse.value = result
//    }
}


//    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
//    val signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

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
