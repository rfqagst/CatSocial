package com.example.catsocial.presentation.screen.catlist

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.catsocial.presentation.components.DescriptionTextField
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
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(16.dp))
                            .size(200.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                        painter = rememberAsyncImagePainter(model = catList.imageUrl ?: ""),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                    Log.d("ListInformasiScreen", "Success: ${catList}")
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = catList.name ?: "No Name Found",
                        modifier = Modifier.padding(start = 20.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 44.sp,
                            fontWeight = FontWeight(700),
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Origin : ${catList.origin ?: "No Origin Found"}",
                        modifier = Modifier.padding(start = 20.dp),
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 44.sp,
                            fontWeight = FontWeight(700),
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DescriptionTextField(
                        modifier = Modifier.padding(start = 20.dp),
                        titleTextField = "Description", value = "${catList.description}"
                    ) {

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Temperament : ${catList.temperament ?: "No Temperament Found"}",
                        modifier = Modifier.padding(start = 20.dp),
                        style = TextStyle(
                            fontSize = 18.sp,
                        )
                    )


                }
            }


        }
    }


}