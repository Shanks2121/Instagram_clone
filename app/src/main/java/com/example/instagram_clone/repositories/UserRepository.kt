package com.example.instagram_clone.repositories

import com.example.instagram_clone.data.ServicesImpl
import com.example.instagram_clone.models.User
import com.example.instagram_clone.sealed.DataResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val ServicesImpl: ServicesImpl,
) {
    suspend fun signInUser(email: String, password: String): DataResponse<User> {
        return ServicesImpl.signInUser(email = email, password = password)
    }

    suspend fun getFakeFeaturedStories() =
        ServicesImpl.getFakeFeaturedStories()
}