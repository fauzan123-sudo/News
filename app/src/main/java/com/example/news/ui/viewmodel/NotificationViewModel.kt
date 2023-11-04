package com.example.news.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.news.data.model.Notification
import com.example.news.data.repository.NotificationRepository
import com.example.news.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val repository: NotificationRepository) :
    ViewModel() {

    private val _notifications: MutableStateFlow<Resource<List<Notification>>> =
        MutableStateFlow(Resource.Loading())
    val notifications: StateFlow<Resource<List<Notification>>> = _notifications

//    fun getNotificationsByNip(nip: String) {
//        viewModelScope.launch {
//            repository.getNotificationsByNip(nip).collect { result ->
//                _notifications.value = result
//            }
//        }
//    }

}