package com.example.instagram_clone


import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import com.example.instagram_clone.utils.APP_LANGUAGE
import com.example.instagram_clone.utils.dataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
@SuppressLint("StaticFieldLeak")
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {

    val currentLanguage = context.dataStore.data.map {
        it[APP_LANGUAGE] ?: "en"
    }
}

val LocalMainViewModel =
    compositionLocalOf<MainViewModel> { error("Main view model is not provided!") }