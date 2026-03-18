package com.example.gymtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gymtracker.ui.exercises.ExerciseListScreen
import com.example.gymtracker.ui.muscles.MuscleListScreen
import com.example.gymtracker.ui.settings.SettingsScreen
import com.example.gymtracker.ui.theme.GymTrackerTheme
import com.example.gymtracker.ui.weight.WeightScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymTrackerTheme {
                GymTrackerApp()
            }
        }
    }
}

@Composable
fun GymTrackerApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "muscles"
    ) {
        composable("muscles") {
            MuscleListScreen(
                onMuscleClick = { muscle ->
                    navController.navigate("exercises/${muscle.id}/${muscle.name}")
                },
                onWeightClick = {
                    navController.navigate("weight")
                },
                onSettingsClick = {
                    navController.navigate("settings")
                }
            )
        }

        composable(
            route = "exercises/{muscleId}/{muscleName}",
            arguments = listOf(
                navArgument("muscleId") { type = NavType.LongType },
                navArgument("muscleName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val muscleId = backStackEntry.arguments?.getLong("muscleId") ?: return@composable
            val muscleName = backStackEntry.arguments?.getString("muscleName").orEmpty()
            ExerciseListScreen(
                muscleId = muscleId,
                muscleName = muscleName,
                onBack = { navController.popBackStack() }
            )
        }

        composable("weight") {
            WeightScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable("settings") {
            SettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
