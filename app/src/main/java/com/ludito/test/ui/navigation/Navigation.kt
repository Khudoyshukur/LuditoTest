package com.ludito.test.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ludito.test.R
import com.ludito.test.ui.screens.profile.ProfileScreen
import com.ludito.test.ui.screens.saved.SavedAddressesScreen
import com.ludito.test.ui.screens.search.SearchScreen
import com.ludito.test.ui.theme.md_color_icon_disabled
import com.ludito.test.ui.theme.md_color_icon_enabled
import kotlinx.serialization.Serializable

/**
 * Created by: androdev
 * Date: 12-03-2025
 * Time: 3:51 PM
 * Email: Kjuraev.001@gmail.com
 */

@Serializable
object SavedAddressesScreen

@Serializable
object SearchScreen

@Serializable
object ProfileScreen

data class TopLevelRoute<T : Any>(val route: T, val icon: Painter)

@Composable
fun LuditoApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            LuditoNavigationBar(
                modifier = Modifier.fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars),
                navController = navController
            )
        }
    ) { paddingValues ->
        LuditoNavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController
        )
    }
}

@Composable
private fun LuditoNavHost(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SavedAddressesScreen
    ) {
        composable<SavedAddressesScreen> { SavedAddressesScreen() }
        composable<SearchScreen> { SearchScreen() }
        composable<ProfileScreen> { ProfileScreen() }
    }
}

@Composable
private fun LuditoNavigationBar(
    modifier: Modifier,
    navController: NavHostController
) {
    val topLevelRoutes = listOf(
        TopLevelRoute(SavedAddressesScreen, painterResource(R.drawable.ic_bottom_nav_saved)),
        TopLevelRoute(SearchScreen, painterResource(R.drawable.ic_bottom_nav_location)),
        TopLevelRoute(ProfileScreen, painterResource(R.drawable.ic_bottom_nav_profile)),
    )

    Surface(
        shadowElevation = 16.dp,
        shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)
    ) {
        Row(
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                )
                .padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(64.dp),
        ) {
            Spacer(Modifier.weight(1f))

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            topLevelRoutes.forEach { topLevelRoute ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.hasRoute(topLevelRoute.route::class)
                } == true

                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            navController.navigate(topLevelRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    painter = topLevelRoute.icon,
                    contentDescription = topLevelRoute.route::class.simpleName,
                    tint = if (isSelected) md_color_icon_enabled else md_color_icon_disabled
                )
            }

            Spacer(Modifier.weight(1f))
        }
    }
}