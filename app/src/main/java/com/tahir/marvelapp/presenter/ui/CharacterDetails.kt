package com.tahir.marvelapp.presenter.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tahir.marvelapp.data.commonDTOs.CharacterDetail
import com.tahir.marvelapp.presenter.viewmodels.CharacterDetailsViewModel


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
// live data variable for managing the lifecycle.
    val loadState by characterViewModel.loadState.observeAsState()
    /*
    mutableStateOf variables that are observed by the composable.
     */
    // mutablestate variable for all the three load operation to show/hide progress bar
    val isComicLoading by remember { characterViewModel.isComicLoading }
    val isSeriesLoading by remember { characterViewModel.isSeriesLoading }
    val isEventsLoading by remember { characterViewModel.isEventLoading }
    val isStoriesLoading by remember { characterViewModel.isStoriesLoading }

// mutablestate variable for getting the list data.

    var marvelDetail by remember { characterViewModel.marvelDetails }
    // val marvelDetail by characterViewModel.marvelDetails.observeAsState()


// getting the information from the view model using launchedeffect to avoid most of the side effects by recomposition.
    if (loadState == false) {
        LaunchedEffect(key1 = true) {
            characterViewModel.getCharacterDetails(id)
        }
    }


    Surface {

        LazyColumn {
            item {
                setMsg(title = "Comics")
                if (isComicLoading) {
                    showProgressDialog()
                }
                marvelDetail?.let {
                    DetailsList(
                        it.filter { it.type.equals("comics") }, isComicLoading
                    )
                }
                Spacer(Modifier.size(10.dp))

                setMsg(title = "Series")
                if (isSeriesLoading) {
                    showProgressDialog()
                }
                marvelDetail?.let {
                    DetailsList(
                        it.filter { it.type.equals("series") }, isSeriesLoading
                    )
                }
                Spacer(Modifier.size(10.dp))
                setMsg(title = "Stories")
                if (isStoriesLoading) {
                    showProgressDialog()
                }
                marvelDetail?.let {
                    DetailsList(
                        it.filter { it.type.equals("stories") }, isStoriesLoading
                    )
                }

                Spacer(Modifier.size(10.dp))
                setMsg(title = "Events")
                if (isEventsLoading) {
                    showProgressDialog()
                }
                marvelDetail?.let {
                    DetailsList(
                        it.filter { it.type.equals("events") }, isEventsLoading
                    )
                }
            }
        }
    }


}

@Composable
fun showProgressDialog() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colors.primary)
    }
}

@Composable
fun DetailsItemView(characterDetail: CharacterDetail) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .wrapContentHeight()
    ) {


        Image(
            rememberAsyncImagePainter(
                characterDetail.imageUrl,
                error = painterResource(com.tahir.marvelapp.R.drawable.image_not_available),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(5.dp),
            contentDescription = ""
        )
        characterDetail.Name?.let {
            Text(
                text = it,
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun DetailsList(profileDetails: List<CharacterDetail>, loading: Boolean) {

    if (profileDetails.isEmpty() && !loading) {
        noData()

    } else {

        if (profileDetails != null) {
            LazyRow(content = {
                items(profileDetails) { detail ->
                    DetailsItemView(characterDetail = detail)


                }
            })

        }
    }


}

@Composable

fun setMsg(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(5.dp),
        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
        text = title
    )

}


@Composable

fun noData() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text("No data found.")

    }

}



