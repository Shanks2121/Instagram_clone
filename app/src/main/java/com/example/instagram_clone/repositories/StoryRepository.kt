package com.example.instagram_clone.repositories

import com.example.instagram_clone.data.ServicesImpl
import com.example.instagram_clone.models.Story
import com.example.instagram_clone.sealed.DataResponse
import javax.inject.Inject

class StoryRepository @Inject constructor(
    private val ServicesImpl: ServicesImpl,
) {
    suspend fun getStories(): DataResponse<List<Story>> {
        return ServicesImpl.getFakeStories()
    }
}