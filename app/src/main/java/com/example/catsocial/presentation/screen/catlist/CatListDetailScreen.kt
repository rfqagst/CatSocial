package com.example.catsocial.presentation.screen.catlist

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.catsocial.util.Resource

@Composable
fun CatListDetailScreen(modifier: Modifier, catId: String, viewModel: CatListViewModel) {


    val catDetail by viewModel.catDetail.collectAsState()

    LaunchedEffect(catId) {
        viewModel.fetchCatById(catId)
    }


    when (catDetail) {
        is Resource.Error -> {
            Log.d("ListInformasiScreen", "Error: ${catDetail.message}")
        }

        is Resource.Idle -> {
            TODO()
        }

        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .size(48.dp)
                )
            }
        }

        is Resource.Success -> {
            val catList = (catDetail as Resource.Success).data

            catList?.let {
                Column(modifier = modifier) {
                    Text(text = catList.name ?: "No Name Found")
                    Text(text = catList.origin ?: "No Origin Found")
                    Text(text = catList.description ?: "No Description Found")
                    Text(text = catList.temperament ?: "No Temperament Found")

                }
            }


        }
    }


}