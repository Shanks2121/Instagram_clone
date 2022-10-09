package com.example.instagram_clone.repositories

import com.example.instagram_clone.data.ServicesImpl
import com.example.instagram_clone.models.Post
import com.example.instagram_clone.sealed.DataResponse
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val ServicesImpl: ServicesImpl,
) {

    suspend fun getFakePosts(): DataResponse<List<Post>> {
        return ServicesImpl.getFakePosts()
    }
}