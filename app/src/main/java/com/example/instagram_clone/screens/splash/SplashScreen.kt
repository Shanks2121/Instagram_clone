package com.example.instagram_clone.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.instagram_clone.R
import com.example.instagram_clone.providers.LocalNavHost
import com.example.instagram_clone.sealed.Screen
import com.example.instagram_clone.ui.theme.Dimension
import com.example.instagram_clone.ui.theme.blueStar


@Composable
fun SplashScreen(splashViewModel: SplashViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(bottom = Dimension.lg.times(2f), top = Dimension.lg.times(3f)),
        contentAlignment = Alignment.Center


    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {


            val controller = LocalNavHost.current
            val isAppLaunchedBefore by splashViewModel.isAppLaunchedBefore.collectAsState(initial = false)

            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.insta_logo_lottie2)
            )
         
            val progress by animateLottieCompositionAsState(
                
                composition,
                iterations = 1,
                restartOnPlay = false,
                speed = 0.9f,
            )
            LaunchedEffect(key1 = progress) {
                if (progress == 1f) {
                    if (isAppLaunchedBefore) {
                        
                        controller.navigate(Screen.Home.route) {
                            popUpTo(Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    } else {
                       
                        controller.navigate(Screen.Login.route) {
                            popUpTo(Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }



            LottieAnimation(
                composition,
                progress,
                modifier = Modifier.size(Dimension.xlIcon.times(1.3f)),
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(Dimension.xs),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Instagram",
                    style = MaterialTheme.typography.h2.copy(
                        fontFamily = blueStar,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )

                )
                Spacer(modifier = Modifier.height(300.dp))
                Text(
                    text = "From",
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.pagePadding.times(0.8f)),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_meta),
                        contentDescription = "meta",
                        modifier = Modifier.size(Dimension.smIcon),
                        tint = MaterialTheme.colors.primary,
                    )
                    Text(
                        text = "Meta",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary,
                    )
                }

            }
        }
    }
}