package com.example.instagram_clone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
//import com.example.instagram_clone.providers.LocalLanguage
import com.example.instagram_clone.providers.LocalNavHost
import com.example.instagram_clone.screens.holder.HolderScreen
import com.example.instagram_clone.ui.theme.InstagramTheme
import com.example.instagram_clone.utils.LocalScreenSize
import com.example.instagram_clone.utils.getScreenSize

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val defaultStatusBarColor = MaterialTheme.colors.background.toArgb()
            var statusBarColor by remember { mutableStateOf(defaultStatusBarColor) }
            window.statusBarColor = statusBarColor

            val mainViewModel: MainViewModel = viewModel(this)
            val navController = rememberNavController()



           
            val size = LocalContext.current.getScreenSize()

            InstagramTheme {
                CompositionLocalProvider(

                    LocalScreenSize provides size,
                    LocalMainViewModel provides mainViewModel,
                    LocalNavHost provides navController
                ) {

                    Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background) {
                        HolderScreen(
                            onStatusBarColorChange = {

                                statusBarColor = it.toArgb()
                            }
                        )
                    }
                }
            }
        }
    }
}