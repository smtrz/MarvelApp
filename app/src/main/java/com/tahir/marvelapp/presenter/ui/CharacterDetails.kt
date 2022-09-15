package com.tahir.marvelapp.presenter.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tahir.marvelapp.data.models.Movie
import com.tahir.marvelapp.presenter.viewmodels.CharacterDetailsViewModel
import com.tahir.pokedex.ui.MarvelAppTheme


/**
 * Character profile screen,
 * @param id , navController
 */
@Composable
fun ProfileScreen(
    id: Int,
    navController: NavController,
    characterViewModel: CharacterDetailsViewModel = hiltViewModel()
) {
    // mutableStateOf variables that are observed by the composable.
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(content = {
            item {
                GenreTitle(genreTitle = "Action Thriller")
                MovieList(
                    movieList = listOf(
                        Movie(
                            1,
                            "Awesome",
                            "Horror",
                            "tahir",
                            "three hours",
                            "lorem ipsum"
                        )
                    )
                )
                Spacer(Modifier.size(10.dp))
                GenreTitle(genreTitle = "Comedy")
                MovieList(
                    movieList = listOf(
                        Movie(
                            1,
                            "Awesome",
                            "Horror",
                            "tahir",
                            "three hours",
                            "lorem ipsum"
                        )
                    )
                )
                Spacer(Modifier.size(10.dp))
                GenreTitle(genreTitle = "Animation")
                MovieList(
                    movieList = listOf(
                        Movie(
                            1,
                            "Awesome",
                            "Horror",
                            "tahir",
                            "three hours",
                            "lorem ipsum"
                        )
                    )
                )
            }
        })
    }




}
@Composable
fun MovieItemView(movie: Movie) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .wrapContentHeight()
    ) {
        Image(
            rememberAsyncImagePainter("https://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg"),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(5.dp),
            contentDescription = ""
        )
        Text(
            text = movie.movieName,
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun MovieList(movieList: List<Movie>) {
    LazyRow(content = {
        items(movieList) { movie ->
            MovieItemView(movie = movie)


        }
    })
}

@Composable

fun GenreTitle(genreTitle: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(5.dp),
        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
        text = genreTitle
    )

}





