package com.example.wellpet

import RemindersScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wellpet.WellPetArgs.PET_PROFILE_ID_ARG
import com.example.wellpet.WellPetRoutes.ADD_EDIT_PET_PROFILE_ROUTE
import com.example.wellpet.WellPetRoutes.PET_PROFILES_ROUTE
import com.example.wellpet.WellPetScreens.ADD_EDIT_PET_PROFILE_SCREEN
import com.example.wellpet.WellPetScreens.BUDGET_DETAIL_ROUTE
import com.example.wellpet.WellPetScreens.CREATE_PET_BUDGET_ROUTE
import com.example.wellpet.WellPetScreens.PET_BUDGET_ROUTE
import com.example.wellpet.WellPetScreens.PET_PROFILES_SCREEN
import com.example.wellpet.WellPetScreens.PET_PROFILE_DETAIL_ROUTE
import com.example.wellpet.WellPetScreens.REMINDERS_ROUTE
import com.example.wellpet.WellPetScreens.ROUTE_HOME
import com.example.wellpet.WellPetScreens.ROUTE_LOGIN
import com.example.wellpet.WellPetScreens.ROUTE_SIGNUP
import com.example.wellpet.WellPetScreens.SETTINGS_ROUTE
import com.example.wellpet.auth.ui.AuthViewModel
import com.example.wellpet.auth.ui.LoginScreen
import com.example.wellpet.auth.ui.SignupScreen
import com.example.wellpet.budget.CreatePetBudgetScreen
import com.example.wellpet.budget.PetBudgetScreen
import com.example.wellpet.home.HomeScreen
import com.example.wellpet.petcare.petprofileaddedit.PetProfileAddEdit
import com.example.wellpet.petcare.petprofiles.PetProfileViewModel
import com.example.wellpet.petcare.petprofiles.PetProfilesScreen
import com.example.wellpet.settings.SettingsScreen

object WellPetScreens {
    const val PET_PROFILES_SCREEN = "Pet Profiles"
    const val ADD_EDIT_PET_PROFILE_SCREEN = "addEditPetProfile"
    const val PET_BUDGET_ROUTE = "Pet Budgets"
    const val CREATE_PET_BUDGET_ROUTE = "Create Pet Budgets"
    const val ROUTE_LOGIN = "login"
    const val ROUTE_SIGNUP = "signup"
    const val ROUTE_HOME = "home"
    const val SETTINGS_ROUTE = "Reminders"
    const val REMINDERS_ROUTE = "Settings"
    const val BUDGET_DETAIL_ROUTE = "Budget Detail"
    const val PET_PROFILE_DETAIL_ROUTE = "Pet Profile Detail"
}

object WellPetArgs {
    const val PET_PROFILE_ID_ARG = "petProfileId"
}
object WellPetRoutes {
    const val PET_PROFILES_ROUTE = PET_PROFILES_SCREEN
    const val ADD_EDIT_PET_PROFILE_ROUTE = ADD_EDIT_PET_PROFILE_SCREEN
}

@Composable
fun NavGraph(navController: NavHostController, viewModel: AuthViewModel, modifier: Modifier = Modifier){
    val petProfileViewModel: PetProfileViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = ROUTE_LOGIN) {
        composable(ROUTE_LOGIN) {
            LoginScreen(viewModel, navController)
        }
        composable(ROUTE_SIGNUP) {
            SignupScreen(viewModel, navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(viewModel, navController)
        }
        composable(SETTINGS_ROUTE) {
            SettingsScreen()
        }
        composable(REMINDERS_ROUTE) {
            RemindersScreen()
        }


        composable(route = PET_BUDGET_ROUTE) {
            PetBudgetScreen(
                onAddBudget = {
                    navController.navigate(CREATE_PET_BUDGET_ROUTE)
                },
                onSelectBudget = { budget ->
                    navController.navigate("$BUDGET_DETAIL_ROUTE/?budgetId=${budget.budgetName}")
                }

            )
        }
        composable(route = CREATE_PET_BUDGET_ROUTE) {
            CreatePetBudgetScreen(
                navController = navController,
                onConfirmBudget = {
                    navController.navigate(PET_BUDGET_ROUTE)
                }

            )

        }
        composable(route = PET_PROFILES_ROUTE) {
            PetProfilesScreen(
                onAddPetProfile = {
                    navController.navigate(ADD_EDIT_PET_PROFILE_ROUTE)
                },
                onSelectPetProfile = {
                    navController.navigate(PET_PROFILE_DETAIL_ROUTE)


                },
                onDeletePetProfile = { petProfileId ->
                    petProfileViewModel.deletePetProfile(petProfileId)
                },
            )
        }

        composable(
            route = ADD_EDIT_PET_PROFILE_ROUTE,
            arguments = listOf(
                navArgument(PET_PROFILE_ID_ARG) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            val curId = entry.arguments?.getString(PET_PROFILE_ID_ARG)
            if (curId == null) {
                PetProfileAddEdit(
                    navController = navController,
                    addNewPetProfileViewModel = hiltViewModel(),
                    onConfirmProfile = {
                        navController.navigate(PET_PROFILES_ROUTE)
                    }
                )
            } else {
            }
        }
    }
}