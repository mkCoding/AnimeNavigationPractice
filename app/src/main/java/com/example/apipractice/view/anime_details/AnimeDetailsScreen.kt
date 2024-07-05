package com.example.apipractice.view.anime_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.apipractice.data.model.Data


@Composable
fun AnimeDetailsScreen(
    anime: Data,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 50.dp),
            text = anime.title,
            style = TextStyle(fontSize = 30.sp)
        )

        HorizontalDivider(
            thickness = 4.dp,
            modifier = Modifier
                .width(400.dp)
                .padding(bottom = 15.dp),
            color = Color.Black
        )

        Column(

            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                painter = rememberImagePainter(
                    data = anime.images.jpg.image_url
                ), contentDescription = "pic"

            )

            Text(
                text = anime.synopsis,
                style = TextStyle(fontSize = 15.sp),
                modifier = Modifier
                    .padding(16.dp) // Adjust padding as needed
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.Top)


            )
        }

    }

}


@Composable
@Preview(showBackground = true)
fun PreviewAnimeDetailsScreen() {
//    val sampleAnime = Data(
//        title = "Sample Anime Title",
//        synopsis = "This is a sample description for the anime."
//    )
//
//    AnimeDetailsScreen(
//        anime = sampleAnime,
//        onBack = {}
//    )
}