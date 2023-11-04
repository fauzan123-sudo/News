package com.example.news.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.news.databinding.FragmentHomeBinding
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("TAG", "Token FCM is : ${task.result} ")
            } else {
                Log.e("TAG", "error token FCM", )
            }

        }

    }

}