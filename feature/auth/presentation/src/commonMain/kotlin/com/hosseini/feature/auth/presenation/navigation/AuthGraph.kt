package com.hosseini.feature.auth.presenation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hosseini.feature.auth.presenation.register.RegisterRoot
import com.hosseini.feature.auth.presenation.register_success.RegisterSuccessRoot

/**
 * NavGraphBuilder extension function — a pattern that keeps each feature's navigation self-contained and out
 * of the app module.
 */

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    onLoginSuccess: () -> Unit
) {
    navigation<AuthGraphRoutes.Graph>(startDestination = AuthGraphRoutes.Register) {

        composable<AuthGraphRoutes.Register> {
            RegisterRoot(onRegisterSuccess = {
                navController.navigate(AuthGraphRoutes.RegisterSuccess(it))
            })
        }

        composable<AuthGraphRoutes.RegisterSuccess> {
            RegisterSuccessRoot()
        }
    }
}