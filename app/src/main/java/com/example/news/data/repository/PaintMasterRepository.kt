package com.example.news.data.repository

import com.example.news.data.model.category.RequestCategory
import com.example.news.data.repository.base.BaseFirebaseRepository
import com.example.news.util.Resource
import com.example.news.util.await
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class PaintMasterRepository @Inject constructor() : BaseFirebaseRepository() {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("paint")

    suspend fun addCategory(
        request: RequestCategory
    ): Resource<Unit> {
        return safeFirebaseCall {
            val newUser =
                RequestCategory(request.id_category, request.code_category, request.name_category)
            val registrationRef = database.push()
            val userId = registrationRef.key
            request.id_category = userId!!
            database.child(request.id_category!!).setValue(newUser).await()
        }
    }

//    suspend fun addCategory(user: RequestRegister): Resource<Unit> {
//        return safeFirebaseCall {
//            val registrationRef = database.push()
//            val userId = registrationRef.key
//            user.id = userId
//            registrationRef.setValue(user)
//        }
//    }

}