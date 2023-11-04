package com.example.news.data.repository

import com.example.news.data.model.auth.RequestRegister
import com.example.news.data.model.auth.User
import com.example.news.data.repository.base.BaseFirebaseRepository
import com.example.news.util.Resource
import com.example.news.util.await
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MyAuthRepository @Inject constructor() : BaseFirebaseRepository() {
    private val database = FirebaseDatabase.getInstance().getReference("user")

    suspend fun userRegistration(user: RequestRegister): Resource<Unit> {
        return safeFirebaseCall {
            val registrationRef = database.push()
            val userId = registrationRef.key
            user.id = userId
            registrationRef.setValue(user)
        }
    }

    fun login3(username: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        val notificationRef = database.child(username)
        val notificationSnapshot = notificationRef.get().await()

        val notification = notificationSnapshot.getValue(User::class.java)
        if (notification != null) {
            emit(Resource.Success(notification))
        } else {
            emit(Resource.Error("Notification not found"))
        }
    }.flowOn(Dispatchers.IO)


    fun login(username: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        val notificationRef = database.child(username)
        val notificationSnapshot = notificationRef.get().await()

        val notification = notificationSnapshot.getValue(User::class.java)
        if (notification != null) {
            emit(Resource.Success(notification))
        } else {
            emit(Resource.Error("Notification not found"))
        }
    }.flowOn(Dispatchers.IO)



    fun userLogin(username: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())

        try {
            val query = database.orderByChild("username").equalTo(username)
            val snapshot = query.get().await()

            if (snapshot.exists()) {
                for (data in snapshot.children) {
                    val user = data.getValue(User::class.java)
                    if (user != null && user.username == username && user.password == password) {
                        emit(Resource.Success(user))
                        return@flow
                    }
                }
            }

            emit(Resource.Error(message = "Login failed. Check your credentials."))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)


}