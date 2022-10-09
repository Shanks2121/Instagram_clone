package com.example.instagram_clone.screens.home


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagram_clone.models.Post
import com.example.instagram_clone.models.Story
import com.example.instagram_clone.repositories.PostRepository
import com.example.instagram_clone.repositories.StoryRepository
import com.example.instagram_clone.sealed.DataResponse
import com.example.instagram_clone.sealed.Error
import com.example.instagram_clone.sealed.UiState
import com.example.instagram_clone.utils.appendOrRemove
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storyRepository: StoryRepository,
    private val postRepository: PostRepository,
) : ViewModel() {
    val storiesUiState = mutableStateOf<UiState>(UiState.Idle)
    val stories: MutableList<Story> = mutableStateListOf()

    val postsUiState = mutableStateOf<UiState>(UiState.Idle)
    val posts: MutableList<Post> = mutableStateListOf()

    val bookmarkedPostsIds = mutableStateListOf<Int>()
    val likedPostsIds = mutableStateListOf<Int>()

    fun getStories() {
        if (storiesUiState.value is UiState.Success) return

        storiesUiState.value = UiState.Loading
        viewModelScope.launch {

            storyRepository.getStories().let { response ->
                when (response) {
                    is DataResponse.Success -> {

                        storiesUiState.value = UiState.Success
                        response.data?.let { responseStories ->
                            stories.addAll(responseStories)
                        }
                    }
                    else -> {

                        storiesUiState.value =
                            UiState.Error(error = response.error ?: Error.Network)
                    }
                }
            }
        }
    }

    fun getPosts() {
        if (postsUiState.value is UiState.Success) return
        postsUiState.value = UiState.Loading
        viewModelScope.launch {

            delay(4000)
            postRepository.getFakePosts().let { response ->
                when (response) {
                    is DataResponse.Success -> {

                        postsUiState.value = UiState.Success
                        response.data?.let { responsePosts ->
                            posts.addAll(responsePosts)
                        }
                    }
                    else -> {

                        postsUiState.value =
                            UiState.Error(error = response.error ?: Error.Network)
                    }
                }
            }
        }
    }

    fun updateLikedPosts(id: Int) {
        likedPostsIds.appendOrRemove(element = id)
    }

    fun updateBookmarkedPosts(id: Int) {
        bookmarkedPostsIds.appendOrRemove(element = id)
    }

}