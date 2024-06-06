package com.example.catsocial.presentation.screen.catlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.catsocial.data.retrofit.response.CatResponse
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

    Column(modifier = modifier.padding(16.dp)) {

        SearchBarKucing(value = searchText, onValueChange = {
            searchText = it
        }, modifier = Modifier.padding(bottom = 24.dp))

        when (catListState) {
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
                val cats = (catListState as Resource.Success<List<CatResponse>>).data
                LazyColumn(modifier = modifier) {
                    items(cats ?: emptyList()) { cat ->
                        CatInformationCard(
                            modifier = Modifier,
                            name = cat.breeds?.firstOrNull()?.name ?: "Unknown Breed",
                            description = cat.breeds?.firstOrNull()?.description
                                ?: "No description available",
                            image = cat.url ?: ""
                        )
                    }
                }
            }

            is Resource.Idle -> {
                // No-op
            }
        }
    }
}