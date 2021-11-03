package com.plcoding.cryptocurrencyappyt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.plcoding.cryptocurrencyappyt.presentation.coin_detail.CoinDetailScreen
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.CoinListScreen
import com.plcoding.cryptocurrencyappyt.presentation.ui.theme.CryptocurrencyAppYTTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppYTTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController =
                        rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route,
                    ) {
                        coinListScreen(navController)
                        coinDetailScreen()
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.coinListScreen(navController: NavController) {
    composable(
        Screen.CoinListScreen.route,
        exitTransition = { _, target ->
            slideOutHorizontally(
                targetOffsetX = { -300 },
                animationSpec = tween(durationMillis = 300)
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = { init, _ ->
            slideInHorizontally(
                initialOffsetX = { -300 },
                animationSpec = tween(durationMillis = 300)
            ) + fadeIn(animationSpec = tween(300))
        }
    ) {
        CoinListScreen(navController = navController)
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.coinDetailScreen() {
    composable(
        Screen.CoinDetailScreen.route + "/{coinId}",
        enterTransition = { _,_ ->
            slideInHorizontally(
                initialOffsetX = { 300 },
                animationSpec = tween(durationMillis = 300)
            ) + fadeIn(animationSpec = tween(300))

        },
        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 300 },
                animationSpec = tween(durationMillis = 300)
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {
        CoinDetailScreen()
    }
}