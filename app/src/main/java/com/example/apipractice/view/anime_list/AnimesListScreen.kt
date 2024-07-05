package com.example.apipractice.view.anime_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apipractice.data.model.Data
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun AnimesListScreen(navController: NavController) {

    //create view model
//    val viewModel: AnimeListViewModel = viewModel()
    val viewModel: AnimeListViewModel = hiltViewModel()
//    //list of all animes from API
    val animesList = viewModel.animeList.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = "All Animes",
            style = TextStyle(fontSize = 30.sp)
        )
        HorizontalDivider(
            thickness = 4.dp,
            color = Color.Black,
            modifier = Modifier.width(400.dp)

        )

        AnimesList(animesList = animesList, navController = navController)
    }


}

@Composable
fun AnimesList(animesList: List<Data>, navController: NavController){
    val myList = listOf("Elephant", "Zebra", "Bird", "Lion", "Whale", "Eagle")
    
    LazyColumn(
        modifier = Modifier.height(750.dp)
    ) {
        items(animesList) {itemElement ->
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(15.dp),
                onClick = {navController.navigate("animeDetails/${itemElement.mal_id}")}

            ) {
                Column (
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ){
                    Text(
                        text = itemElement.title_english,
                        style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)

                    )
//                    Text(text = itemElement.rating)
//                    Text(text = itemElement.year.toString())

//                    Text(text = itemElement)
                }


            }

        }

    }

}

@Composable
@Preview(showBackground = true)
fun PreviewAnimesListScreen() {
    val navController = rememberNavController()
    AnimesListScreen(navController = navController)
}