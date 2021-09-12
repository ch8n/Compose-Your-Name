package io.github.ch8n.whatis.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import io.github.ch8n.whatis.ui.screens.home.HomeScreen
import io.github.ch8n.whatis.ui.screens.nameReveal.NameRevealScreen
import io.github.ch8n.whatis.ui.screens.nameform.NameFormScreen
import io.github.ch8n.whatis.ui.screens.stories.StoriesScreen


sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object NameForm : Screen("NameForm")
    object NameReveal : Screen("NameReveal")
    object Stories : Screen("Stories")

    fun withArgs(vararg attributes: Pair<String, String>): String {
        return route.plus(attributes.joinToString(separator = "") { (_, values) -> "/$values" })
    }
}


@ExperimentalMaterialApi
@Composable
fun WhatisNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.NameForm.route) {
            NameFormScreen(navController = navController)
        }

        composable(
            Screen.NameReveal.route
                .plus("/{firstName}")
                .plus("/{lastName}"),
            arguments = listOf(
                navArgument("firstName") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("lastName") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
            )
        ) { _entry ->
            NameRevealScreen(
                navController = navController,
                firstName = requireNotNull(_entry.arguments?.getString("firstName")),
                lastName = requireNotNull(_entry.arguments?.getString("lastName")),
            )
        }

        composable(Screen.Stories.route) {
            StoriesScreen(navController = navController)
        }
    }
}
