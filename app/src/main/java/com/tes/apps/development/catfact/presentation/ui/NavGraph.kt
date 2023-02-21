package com.tes.apps.development.catfact.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tes.apps.development.catfact.domain.model.CatFactModel
import com.tes.apps.development.catfact.presentation.detail.DetailScreen
import com.tes.apps.development.catfact.presentation.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route= Screen.Detail.route){
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<CatFactModel>("catFact")
            DetailScreen(catFactModel = result, navController = navController)
        }
    }

}