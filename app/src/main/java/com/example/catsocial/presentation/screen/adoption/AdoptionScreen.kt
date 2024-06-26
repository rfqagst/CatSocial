package com.example.catsocial.presentation.screen.adoption

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.catsocial.presentation.components.AdoptionCard
import com.example.catsocial.presentation.components.BannerCard
import com.example.catsocial.presentation.components.SearchBarKucing
import com.example.catsocial.presentation.navigation.Screen
import com.example.catsocial.util.Resource
import com.example.catsocial.util.byteArrayToImageBitmap
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


@OptIn(ExperimentalPermissionsApi::class, ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SharedTransitionScope.AdoptionScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: AdoptionViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    var searchText by remember { mutableStateOf("") }

    val adoptionData by viewModel.allAdoptions.collectAsState()

    val searchResult by viewModel.searchAdoption.collectAsState()

    val permissions = listOf(
        Manifest.permission.USE_EXACT_ALARM,
        Manifest.permission.POST_NOTIFICATIONS,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)

    LaunchedEffect(key1 = Unit) {
        multiplePermissionsState.launchMultiplePermissionRequest()
    }


    Column(modifier.padding(16.dp)) {
        BannerCard(
            modifier = Modifier.padding(top = 16.dp, bottom = 26.dp),
            navController = navController
        )
        Text(text = "Mulai Adopsi Anabul", fontWeight = FontWeight.SemiBold)

        SearchBarKucing(
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.searchCatByRace(searchText)
            },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(20.dp))

        val dataToShow = if (searchText.isEmpty()) adoptionData else searchResult


        when (dataToShow) {
            is Resource.Error -> {
                Log.d("AdoptionScreen", "Error: ${adoptionData.message}")

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
                val adoption = dataToShow.data

                adoption?.let { adopts ->
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize()

                    ) {
                        items(adopts.size) { index ->
                            val adoptions = adopts[index]

                            val adoptionImage = byteArrayToImageBitmap(adoptions.image)

                            AdoptionCard(
                                modifier = Modifier.clickable {
                                    navController.navigate(Screen.AdoptionDetail.route + "/${adoptions.id}")
                                    Log.d("AdoptionScreen", "Success: ${adoptions.id}")
                                },
                                image = adoptionImage!!,
                                name = adoptions.name,
                                gender = adoptions.gender,
                                usia = adoptions.age,
                                ras = adoptions.race,
                                imageModifier = Modifier.sharedBounds(
                                    sharedContentState = rememberSharedContentState(key = "image/{${adoptions.id}}"),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    boundsTransform = { _, _ ->
                                        tween(durationMillis = 500)
                                    }
                                ),
                                textModifier = Modifier.sharedElement(
                                    state = rememberSharedContentState(key = "text/{${adoptions.id}}"),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    boundsTransform = { _, _ ->
                                        tween(durationMillis = 500)
                                    }
                                ))
                        }
                    }
                }


            }
        }


    }
}