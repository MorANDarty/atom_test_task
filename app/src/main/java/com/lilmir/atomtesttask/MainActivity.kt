package com.lilmir.atomtesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lilmir.atomtesttask.feature.chargerslist.ui.CityDetailsScreen
import com.lilmir.atomtesttask.feature.cities.ui.CityListScreen
import com.lilmir.atomtesttask.ui.theme.AtomTestTaskTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AtomTestTaskTheme {
                NavHost(navController = navController, startDestination = "cityList") {
                    composable("cityList") {
                        CityListScreen(navController = navController)
                    }
                    composable(
                        "cityDetails/{cityName}",
                        arguments = listOf(navArgument("cityName") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
                        CityDetailsScreen(cityName, navController)
                    }
                }
            }
        }
    }
}
