package com.example.miniproyecto01


import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "registro_screen"
    ) {
        // Pantalla 1: Formulario de Registro
        composable("registro_screen") {
            RegistroScreen(onNavigateToDetalle = { matricula, nombre, carrera, turno, activo ->
                navController.navigate(
                    "detalle_screen/${Uri.encode(matricula)}/${Uri.encode(nombre)}/${Uri.encode(carrera)}/${Uri.encode(turno)}/$activo"
                )
            })
        }

        // Pantalla 2: Detalle (recibe los 5 parámetros)
        composable(
            route = "detalle_screen/{matricula}/{nombre}/{carrera}/{turno}/{activo}",
            arguments = listOf(
                navArgument("matricula") { type = NavType.StringType },
                navArgument("nombre") { type = NavType.StringType },
                navArgument("carrera") { type = NavType.StringType },
                navArgument("turno") { type = NavType.StringType },
                navArgument("activo") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val matricula = backStackEntry.arguments?.getString("matricula") ?: ""
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val carrera = backStackEntry.arguments?.getString("carrera") ?: ""
            val turno = backStackEntry.arguments?.getString("turno") ?: ""
            val activo = backStackEntry.arguments?.getString("activo")?.toBoolean() ?: true

            DetalleScreen(
                matricula = matricula,
                nombre = nombre,
                carrera = carrera,
                turno = turno,
                activo = activo,
                onVolver = { navController.popBackStack() }
            )
        }
    }
}