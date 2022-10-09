package com.example.instagram_clone.screens.login


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagram_clone.R
import com.example.instagram_clone.UserSession
import com.example.instagram_clone.models.User
import com.example.instagram_clone.repositories.UserRepository
import com.example.instagram_clone.sealed.DataResponse
import com.example.instagram_clone.sealed.Error
import com.example.instagram_clone.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val uiState = mutableStateOf<UiState>(UiState.Idle)
    val recentUser = User(
        userId = 1,
        userName = "Shanks",
        profile = R.drawable.profile
    )
    fun authenticateUser(onAuthenticated: () -> Unit, onAuthenticationFailed: () -> Unit) {
        uiState.value = UiState.Loading
        /** We will use the coroutine so that we don't block our dear : The UiThread */
        viewModelScope.launch {
            userRepository.signInUser(
                email = "yadavshashank700@gmail.com",
                password = "Pokemon@",
            ).let {
                when (it) {
                    is DataResponse.Success -> {
                        it.data?.let {
                            /** Authenticated successfully */
                            uiState.value = UiState.Success
                            UserSession.user = it
                            onAuthenticated()
                        }
                    }
                    is DataResponse.Error -> {
                        /** An error occurred while authenticating */
                        uiState.value = UiState.Error(error = it.error ?: Error.Network)
                        onAuthenticationFailed()
                    }
                }
            }
        }
    }
}