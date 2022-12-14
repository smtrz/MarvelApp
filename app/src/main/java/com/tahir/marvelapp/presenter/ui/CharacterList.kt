package com.tahir.marvelapp.presenter.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.tahir.marvelapp.data.commonDTOs.MarvelCharacter
import com.tahir.marvelapp.presenter.navigation.Screen
import com.tahir.marvelapp.presenter.viewmodels.CharacterListViewModel

/**
 * MarvelCardItem design
 * @param marvelCharacter
 */
@Composable
fun MarvelCardItem(
    marvelCharacter: MarvelCharacter,
    navController: NavController
) {

    Card(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    Screen.CharacterDetailscreen.withArgs(
                        marvelCharacter.marvel_id
                    )
                )

            },
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp


    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            var imageUrl =
                marvelCharacter.imageUrl
            imageUrl?.let {
                ImageLoader(it)
            }
            Spacer(modifier = Modifier.width(8.dp))
            marvelCharacter.Name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

/**
 * marvelList design
 * @param navController
 */
@Composable
fun MarvelList(
    navController: NavController,
    marvelListViewModel: CharacterListViewModel = hiltViewModel()
) {
    val characterPagingList = marvelListViewModel.characterPager.collectAsLazyPagingItems()

    LazyColumn {

        items(characterPagingList) { results ->

            if (results != null) {

                MarvelCardItem(results, navController)
            }


        }

    }
    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ) {
        characterPagingList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)

                }
                loadState.append is LoadState.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)

                }
                loadState.refresh is LoadState.Error -> {
                    val e = characterPagingList.loadState.refresh as LoadState.Error
                    if (characterPagingList.itemCount == 0) {
                        RetrySection(error = e.error.message!!) {
                            characterPagingList.retry()
                        }
                    }

                }
            }

        }


    }
}

/**
 * RetrySection design
 * @param error
 */
@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = androidx.compose.ui.graphics.Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}
