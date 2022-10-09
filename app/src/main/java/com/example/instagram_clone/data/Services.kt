package com.example.instagram_clone.data

import com.example.instagram_clone.sealed.DataResponse

import com.example.instagram_clone.models.Featured

import com.example.instagram_clone.models.Post
import com.example.instagram_clone.models.Story
import com.example.instagram_clone.models.User


interface Services {
    suspend fun signInUser(email: String, password: String): DataResponse<User>

    suspend fun getFakePosts(): DataResponse<List<Post>>

    suspend fun getFakeStories(): DataResponse<List<Story>>

    suspend fun getFakeFeaturedStories(): DataResponse<List<Featured>>

    suspend fun findStoryById(storyId: Int): DataResponse<Story?>

    suspend fun findPostById(postId: Int): DataResponse<Post?>

    suspend fun getFakeUsers(userName: String): DataResponse<List<User>>

    suspend fun findUserByUsername(userName: String): DataResponse<User?>


}