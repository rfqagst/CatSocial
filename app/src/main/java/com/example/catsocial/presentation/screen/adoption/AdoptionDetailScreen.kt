package com.example.catsocial.presentation.screen.adoption

import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.catsocial.presentation.components.CategoryChip
import com.example.catsocial.presentation.navigation.Screen
import com.example.catsocial.ui.theme.GreyPrimary
import com.example.catsocial.util.Resource
import com.example.catsocial.util.byteArrayToImageBitmap

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AdoptionDetailScreen(
    modifier: Modifier,
    viewModel: AdoptionViewModel,
    adoptionId: String,
    navController: NavHostController,
    animatedVisibilityScope: AnimatedVisibilityScope

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
                val imageBitmap = byteArrayToImageBitmap(it.image)

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(GreyPrimary)
                        .verticalScroll(rememberScrollState())
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                    )
                    {
                        Column {
                            Box(
                                modifier = Modifier
                                    .padding(top = 14.dp)
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .clip(RoundedCornerShape(10.dp))
                            ) {

                                Image(
                                    bitmap = imageBitmap!!,
                                    contentDescription = it.description,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .sharedBounds(
                                            sharedContentState = rememberSharedContentState(key = "image/{${it.id}}"),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                            boundsTransform = { _, _ ->
                                                tween(durationMillis = 1000)
                                            }
                                        )
                                        .fillMaxSize()

                                )
                            }
                            Spacer(modifier = Modifier.height(22.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            )
                            {
                                CategoryChip(
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(40.dp),
                                    text = it.gender
                                )
                                CategoryChip(
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(40.dp),
                                    text = it.age
                                )
                                CategoryChip(
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(40.dp),
                                    text = it.weight
                                )
                            }
                            Text(
                                text = it.name,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .sharedElement(
                                        state = rememberSharedContentState(key = "text/{${it.id}}"),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        boundsTransform = { _, _ ->
                                            tween(durationMillis = 500)
                                        })
                                    .padding(top = 25.dp)

                            )
                            Text(
                                text = it.race,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                            )
                            Row {
                                Text(
                                    text = "Majikan : ",
                                    fontSize = 16.sp, fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(
                                        top = 4.dp,
                                        bottom = 16.dp
                                    )
                                )
                                Text(
                                    text = "Libryan",
                                    fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(
                                        top = 4.dp,
                                        bottom = 16.dp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            )
                            {
                                Row(modifier = Modifier) {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = "location",
                                        tint = Color(0xFFFF6B35),
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = it.kota,
                                        fontSize = 16.sp, fontWeight = FontWeight.Normal,
                                        modifier = Modifier
                                    )
                                }
                                Text(
                                    text = "Lihat Map",
                                    textDecoration = TextDecoration.Underline,
                                    color = Color(0xFFFF6B35),
                                    modifier = Modifier
                                        .padding(start = 8.dp, end = 10.dp)
                                        .clickable {
                                            navController.navigate(Screen.Map.route + "/${it.id}")
                                        }
                                )
                            }
                            Spacer(modifier = Modifier.height(22.dp))
                            Text(
                                text = "Deskripsi",
                                fontSize = 18.sp, fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                            )
                            Spacer(modifier = Modifier.height(7.dp))
                            Text(
                                text = it.description,
                                fontSize = 16.sp, fontWeight = FontWeight.Normal,
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        }
    }


}


