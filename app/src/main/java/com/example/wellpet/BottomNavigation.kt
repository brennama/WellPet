package com.example.wellpet

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.wellpet.WellPetScreens.PET_BUDGET_ROUTE
import com.example.wellpet.WellPetScreens.PET_PROFILES_SCREEN
import com.example.wellpet.WellPetScreens.REMINDERS_ROUTE
import com.example.wellpet.WellPetScreens.ROUTE_HOME
import com.example.wellpet.WellPetScreens.SETTINGS_ROUTE


@Composable
fun WellPetBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavItem>
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.surface
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { item ->
            Surface {
                BottomNavigationItem(
                    icon = @Composable {
                        val iconImageVector = ImageVector.vectorResource(id = item.iconResId)
                        Icon(
                            imageVector = iconImageVector,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = false
                        }
                    },
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val iconResId: Int // Resource ID of your SVG image
)

val bottomNavItems = listOf(
    BottomNavItem(ROUTE_HOME, "Home", R.drawable.home_icon),
    BottomNavItem(PET_PROFILES_SCREEN, "Pet Care", R.drawable.pet_care_icon),
    BottomNavItem(REMINDERS_ROUTE, "Reminders", R.drawable.reminders_icon),
    BottomNavItem(PET_BUDGET_ROUTE, "Budgeting", R.drawable.budgeting_icon),
    BottomNavItem(SETTINGS_ROUTE, "Settings", R.drawable.settings_icon)
)