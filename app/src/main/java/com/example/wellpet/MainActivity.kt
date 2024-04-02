package com.example.wellpet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.wellpet.auth.ui.AuthViewModel
import com.example.wellpet.ui.theme.WellPetTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WellPetTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        WellPetBottomNavigation(navController, bottomNavItems)
                    }
                ) {innerPadding ->
                    NavGraph(navController, viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
