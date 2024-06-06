package com.example.catsocial.presentation.screen.adoption

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
fun AdoptionDetailScreen(
    modifier: Modifier,
    viewModel: AdoptionViewModel,
    adoptionId: String
) {

    val adoptionDetail by viewModel.adoptionDetail.collectAsState()

    LaunchedEffect(adoptionId) {
        viewModel.getCatById(adoptionId.toInt())
    }


    when (adoptionDetail) {
        is Resource.Error -> {
            Log.d("ListInformasiScreen", "Error: ${adoptionDetail.message}")

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
            val adoptionList = (adoptionDetail as Resource.Success).data
            Log.d("ListInformasiScreen", "Success: ${adoptionList?.name}")
            adoptionList?.let {
                Column(modifier = modifier) {
                    Text(text = adoptionList.name)
                    Text(text = adoptionList.description)
                }
            }
        }
    }


}