package com.example.apipractice.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apipractice.data.model.Data
import com.example.apipractice.view.anime_details.AnimeDetailsScreen
import com.example.apipractice.view.anime_list.AnimeListViewModel
import com.example.apipractice.view.anime_list.AnimesListScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun AppNavGraph() {

    val navController = rememberNavController()
    val animeListViewModel: AnimeListViewModel = hiltViewModel()

    // Set up navigation using NavHost and rememberNavController()
    NavHost(navController = navController, startDestination = "animeListScreen") {
        composable("animeListScreen") {
            // Access ViewModel using viewModel() within AnimesListScreen
            AnimesListScreen(navController = navController)
        }

        composable(
            "animeDetails/{malId}",
                    arguments = listOf(
                    navArgument("malId") { type = NavType.IntType })
        ) { backStackEntry ->
            val malId = backStackEntry.arguments?.getInt("malId")
//            var anime = getAnimeById(malId, animeListViewModel) //retrieve actual anime from the Api
            val anime = animeListViewModel.animeList.collectAsState().value.find { it.mal_id == malId }

            if (anime != null) {
                //pass the associated anime to AnimeDetailsScreen
                AnimeDetailsScreen(anime = anime) { navController.popBackStack() }
            }
        }


    }
}

// Function to fetch anime by malId from ViewModel
fun getAnimeById(malId: Int?, animeListViewModel: AnimeListViewModel): Data? {
    val animeList = animeListViewModel.animeList.value
    return animeList.find { it.mal_id == malId }
}