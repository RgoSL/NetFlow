package com.example.netflow.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.netflow.ui.screens.LoginScreen
import com.example.netflow.ui.screens.HabitList
import com.example.netflow.ui.screens.HabitForm
import com.example.netflow.ui.screens.Stats
import com.example.netflow.viewModel.AuthViewModel
import com.example.netflow.viewModel.HabitViewModel
import androidx.compose.runtime.collectAsState
import com.example.netflow.ui.screens.RegisterScreen

@Composable
fun NavFlow() {

    val navController = rememberNavController()

    val authViewModel: AuthViewModel = viewModel()
    val habitViewModel: HabitViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate("habitsList") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onGoToRegister = {
                    navController.navigate("register")
                }

            )
        }

        composable("habitsList") {
            HabitList(
                viewModel = habitViewModel,
                onAddClick = { navController.navigate("addHabit") },
                onEditClick = { habit ->
                    navController.navigate("editHabit/${habit.id}")
                }
            )
        }

        composable("addHabit") {
            HabitForm(
                habit = null,
                onSave = { habit ->
                    habitViewModel.addHabit(habit) {
                        navController.popBackStack()
                    }
                }
            )
        }

        composable(
            route = "editHabit/{habitId}",
            arguments = listOf(navArgument("habitId") { type = NavType.StringType })
        ) { entry ->
            val habitId = entry.arguments?.getString("habitId") ?: ""
            val habit = habitViewModel.getById(habitId)

            HabitForm(
                habit = habit,
                onSave = { updated ->
                    habitViewModel.updateHabit(updated) {
                        navController.popBackStack()
                    }
                }
            )
        }

        composable("stats") {
            Stats(habits = habitViewModel.habits.collectAsState().value)
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate("login") },
                onBackToLogin = { navController.popBackStack() }
            )
        }
    }
}