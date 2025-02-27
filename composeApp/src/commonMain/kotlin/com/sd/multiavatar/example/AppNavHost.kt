package io.github.mew22.example

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.mew22.example.playercreation.PlayerCreationRoute
import io.github.mew22.example.playercreation.playerCreationScreen
import kotlin.reflect.KClass

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: KClass<*> = PlayerCreationRoute::class,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        playerCreationScreen()
    }
}
