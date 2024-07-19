package com.bugrakstudios.moviecompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bugrakstudios.moviecompose.presentation.movies.views.MovieScreen
import com.bugrakstudios.moviecompose.presentation.ui.theme.MovieComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    val navController = rememberNavController()
                    Scaffold(

                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = Screen.MovieScreen.route,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable(route = Screen.MovieScreen.route) {
                                MovieScreen(navController = navController)
                            }
                            composable(route = Screen.MovieDetailScreen.route) {
                                // MovieDetailScreen composable here
                            }
                        }
                    }
                }
            }
        }
    }
}

