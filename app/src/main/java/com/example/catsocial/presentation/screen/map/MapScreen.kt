package com.example.catsocial.presentation.screen.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.catsocial.R
import com.example.catsocial.presentation.screen.adoption.AdoptionViewModel
import com.example.catsocial.ui.theme.CatSocialTheme
import com.example.catsocial.util.Resource
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    adoptionId : String,
    viewModel: AdoptionViewModel
) {
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }
    val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = true)) }

    LaunchedEffect(adoptionId) {
        viewModel.getCatById(adoptionId.toInt())
    }

    val adoptionDetail by viewModel.adoptionDetail.collectAsState()

    when (adoptionDetail) {
        is Resource.Error -> {
            Log.d("AdoptionScreen", "Error: ${adoptionDetail.message}")

        }

        is Resource.Idle -> {
            // Do nothing
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
            val adoptionData = adoptionDetail.data

            adoptionData?.let {

                val userLocation = LatLng(adoptionData.latitude, adoptionData.longitude)
                Box(
                    modifier = Modifier.padding(16.dp)
                ) {
                    val context = LocalContext.current
                    val iconBitmap = getResizedBitmap(context, R.drawable.ic_paw, 32, 32)
                    val cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(userLocation, 20f)
                    }

                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        properties = properties,
                        uiSettings = uiSettings
                    ) {
                        if (iconBitmap != null) {
                            MarkerInfoWindow(
                                state = MarkerState(userLocation),
                                icon = BitmapDescriptorFactory.fromBitmap(iconBitmap)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(24.dp))
                                        .background(Color.LightGray)
                                        .padding(24.dp)
                                ) {
                                    Text(
                                        "Mesjid Raya",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        "Salah satu mesjid terbesar di Sumatera Barat ",
                                        fontWeight = FontWeight.Medium,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }


    }


}

fun getResizedBitmap(context: Context, marker: Int, width: Int, height: Int): Bitmap? {
    val options = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }
    BitmapFactory.decodeResource(context.resources, marker, options)

    val scaleFactor = minOf(options.outWidth / width, options.outHeight / height)
    options.inJustDecodeBounds = false
    options.inSampleSize = scaleFactor

    return BitmapFactory.decodeResource(context.resources, marker, options)
}

//@Preview
//@Composable
//fun MapsPreview() {
//    val navController = rememberNavController()
//    CatSocialTheme {
//        MapsScreen(modifier = Modifier.fillMaxSize(), navController = navController)
//    }
//}
