package com.example.instagram_clone.screens.holder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.instagram_clone.components.AppBottomNav
import com.example.instagram_clone.providers.LocalNavHost
import com.example.instagram_clone.screens.home.HomeScreen
import com.example.instagram_clone.screens.login.LoginScreen
import com.example.instagram_clone.screens.search.SearchScreen
import com.example.instagram_clone.screens.splash.SplashScreen
import com.example.instagram_clone.sealed.Screen

@Composable
fun HolderScreen(
    onStatusBarColorChange: (color: Color) -> Unit,
) {
    val destinations = remember {
        listOf(Screen.Home, Screen.Search, Screen.AddPost,Screen.Notifications,Screen.Profile)
    }
    val controller = LocalNavHost.current
    val currentDestinationAsState = getRoute(navController = controller)

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = controller,
                startDestination = Screen.Splash.route
            ) {
                composable(Screen.Splash.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    SplashScreen()
                }
                composable(Screen.Login.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    LoginScreen()
                }
                composable(Screen.Home.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    HomeScreen()
                }
                composable(Screen.Search.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    SearchScreen()
                }
                composable(Screen.AddPost.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                }
                composable(Screen.Notifications.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)

                }
                composable(Screen.Profile.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)

                }
                composable(Screen.Chats.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)

                }
            }
            Divider()
            if (currentDestinationAsState in destinations.map { it.route }) {
                AppBottomNav(
                    activeRoute = currentDestinationAsState,
                    backgroundColor = MaterialTheme.colors.background,
                    bottomNavDestinations = destinations,
                    onActiveRouteChange = {
                        if (it != currentDestinationAsState) {

                            controller.navigate(it) {
                                popUpTo(Screen.Home.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}




@Composable
fun getRoute(navController: NavHostController): String {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route ?: "splash"
}
