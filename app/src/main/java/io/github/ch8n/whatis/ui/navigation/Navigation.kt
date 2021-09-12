package io.github.ch8n.whatis.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.ch8n.whatis.ui.screens.home.HomeScreen
import io.github.ch8n.whatis.ui.screens.nameReveal.NameRevealScreen
import io.github.ch8n.whatis.ui.screens.nameform.NameFormScreen
import io.github.ch8n.whatis.ui.screens.stories.StoriesScreen


enum class Screen {
    Home,
    NameForm,
    NameReveal,
    Stories
}

@ExperimentalMaterialApi
@Composable
fun WhatisNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.NameReveal.name
    ) {
        composable(Screen.Home.name) {
            HomeScreen(navController = navController)
        }

        composable(Screen.NameForm.name) {
            NameFormScreen(navController = navController)
        }

        composable(Screen.NameReveal.name) {
            NameRevealScreen(navController = navController)
        }

        composable(Screen.Stories.name) {
            StoriesScreen(navController = navController)
        }
    }
}
