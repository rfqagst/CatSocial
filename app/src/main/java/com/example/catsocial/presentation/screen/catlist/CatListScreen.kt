package com.example.catsocial.presentation.screen.catlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.catsocial.presentation.components.CatInformationCard
import com.example.catsocial.presentation.components.SearchBarKucing
import com.example.catsocial.ui.theme.OrangePrimary
import com.example.catsocial.util.Resource

@Composable
fun CatListScreen(
    modifier: Modifier,
    viewModel: CatListViewModel
) {

    var searchText by remember { mutableStateOf("") }

    val catListState by viewModel.cats.collectAsState()
    val catImage by viewModel.imageCats.collectAsState()

    val searchResult by viewModel.searchCats.collectAsState()

    Column(modifier = modifier.padding( horizontal = 16.dp)) {

        SearchBarKucing(value = searchText, onValueChange = {
            searchText = it
            viewModel.searchCat(searchText)
        }, modifier = Modifier.padding(bottom = 24.dp))

        val dataToShow = if (searchText.isEmpty()) catListState else searchResult

        when (dataToShow) {
            is Resource.Error -> {
                Text(text = (catListState as Resource.Error).message ?: "An error occurred")
            }

            is Resource.Loading -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(60.dp),
                        color = OrangePrimary,
                    )
                }


            }

            is Resource.Success -> {
                val catsList = dataToShow.data
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    catsList?.let { catItems ->
                        items(catItems.size) { index ->
                            val currentCat = catItems[index]
                            CatInformationCard(
                                modifier = Modifier,
                                name = currentCat.name ?: "",
                                description = currentCat.description ?: "",
                                image = currentCat.imageUrl ?: "",
                            )
                        }
                    }


                }
            }

            is Resource.Idle -> {
                // No-op
            }
        }
    }
}