package com.example.catsocial.presentation.screen.adoption

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import coil.compose.rememberAsyncImagePainter
import com.example.catsocial.R
import com.example.catsocial.data.room.entity.Cat
import com.example.catsocial.presentation.components.DescriptionTextField
import com.example.catsocial.presentation.components.DropdownField
import com.example.catsocial.presentation.components.DropdownFieldWithTitle
import com.example.catsocial.presentation.components.LargeBtn
import com.example.catsocial.presentation.components.NormalTextField
import com.example.catsocial.presentation.components.NormalTextFieldTrailingText
import com.example.catsocial.presentation.components.SmallBtn
import com.example.catsocial.ui.theme.GreyPrimary
import com.example.catsocial.util.Resource
import com.example.catsocial.util.uriToByteArray
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

@Composable
fun AddAdoptionScreen(modifier: Modifier, viewModel: AdoptionViewModel) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    var namaAnabul by rememberSaveable { mutableStateOf("") }
    var rasAnabul by rememberSaveable { mutableStateOf("") }
    var umurAnabul by rememberSaveable { mutableStateOf("") }
    var beratAnabul by rememberSaveable { mutableStateOf("") }
    var kotaAnabul by rememberSaveable { mutableStateOf("") }
    var deskripsiAnabul by rememberSaveable { mutableStateOf("") }


    val optionsUmur = listOf("Tahun", "Bulan")
    var selectedValueUmur by rememberSaveable { mutableStateOf("Tahun") }
    var expandedUmur by rememberSaveable { mutableStateOf(false) }


    val optionsKelamin = listOf("Jantan", "Betina")
    var selectedValueKelamin by rememberSaveable { mutableStateOf("Jantan") }
    var expandedKelamin by rememberSaveable { mutableStateOf(false) }


    val insertState by viewModel.insertAdoptionState.collectAsState()

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
        }

    val context = LocalContext.current

    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    fun getCurrentLocation(onLocationReceived: (LatLng) -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val currentLatLng = LatLng(it.latitude, it.longitude)
                onLocationReceived(currentLatLng)
            }
        }
    }

    Column(
        modifier
            .background(GreyPrimary)
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(255.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White), contentAlignment = Alignment.Center
        ) {

            if (selectedImageUri != null) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = rememberAsyncImagePainter(model = selectedImageUri),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }

            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.size(45.dp),
                        painter = painterResource(id = R.drawable.ic_gallery),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    SmallBtn(
                        text = "Unggah Foto",
                        onClick = { launcher.launch("image/*") },
                        modifier = Modifier
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(32.dp))
        NormalTextField(
            modifier = Modifier.fillMaxWidth(),
            titleTextField = "Nama Anabul",
            value = namaAnabul,
            onValueChange = { namaAnabul = it },
            placeholderText = "Ikbal, Asep, Micho"

        )
        Spacer(modifier = Modifier.height(16.dp))
        NormalTextField(
            modifier = Modifier.fillMaxWidth(),
            titleTextField = "Ras Anabul",
            value = rasAnabul,
            onValueChange = { rasAnabul = it },
            placeholderText = "Persia Jawa, Anggora, Sphinx, Kampung"
        )
        Spacer(modifier = Modifier.height(16.dp))


        Row(modifier = Modifier.fillMaxWidth()) {
            NormalTextField(
                modifier = Modifier.weight(0.7f),
                titleTextField = "Umur Anabul",
                value = umurAnabul,
                onValueChange = { umurAnabul = it }, placeholderText = "1,3,4,2"
            )

            Spacer(modifier = Modifier.width(8.dp))

            DropdownField(
                modifier = Modifier
                    .weight(0.4f)
                    .align(Alignment.Bottom),
                selectedValue = selectedValueUmur,
                isExpanded = expandedUmur,
                onExpandedChange = {
                    expandedUmur = !expandedUmur
                },
                items = optionsUmur,
                onValueChange = {
                    selectedValueUmur = it
                }
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        NormalTextFieldTrailingText(
            modifier = Modifier.fillMaxWidth(),
            titleTextField = "Berat Anabul",
            value = beratAnabul,
            onValueChange = { beratAnabul = it }, placeholderText = "2,5,6"
        )
        Spacer(modifier = Modifier.height(16.dp))


        DropdownFieldWithTitle(
            modifier = Modifier.fillMaxWidth(),
            titleTextField = "Kelamin Anabul",
            isExpanded = expandedKelamin,
            selectedValue = selectedValueKelamin,
            onExpandedChange = {
                expandedKelamin = !expandedKelamin
            },
            items = optionsKelamin,
            onValueChange = {
                selectedValueKelamin = it
            }
        )


        Spacer(modifier = Modifier.height(16.dp))

        NormalTextField(
            modifier = Modifier.fillMaxWidth(),
            titleTextField = "Kota",
            value = kotaAnabul,
            onValueChange = { kotaAnabul = it }, placeholderText = "Sumendang, Ngawi, Boyolali"
        )
        Spacer(modifier = Modifier.height(16.dp))

        DescriptionTextField(
            modifier = Modifier.fillMaxWidth(),
            titleTextField = "Deskripsi Anabul",
            value = deskripsiAnabul,
            onValueChange = { deskripsiAnabul = it })

        Spacer(modifier = Modifier.height(38.dp))

        LargeBtn(
            text = "Adopsikan",
            onClick = {
                getCurrentLocation { currentLatLng ->
                    selectedImageUri?.let { uri ->
                        val imageData = uriToByteArray(context, uri)
                        val umur = "$umurAnabul $selectedValueUmur"
                        val weight = "$beratAnabul KG"
                        imageData?.let {
                            val cat = Cat(
                                name = namaAnabul,
                                age = umur,
                                weight = weight,
                                gender = selectedValueKelamin,
                                race = rasAnabul,
                                description = deskripsiAnabul,
                                image = it,
                                kota = kotaAnabul,
                                latitude = currentLatLng.latitude,
                                longitude = currentLatLng.longitude,
                            )
                            viewModel.insertAdoption(cat)
                            Log.d(
                                "Location",
                                "Latitude: ${currentLatLng.latitude}, Longitude: ${currentLatLng.longitude}"
                            )
                        }
                    }
                }
            },
            modifier = Modifier.bounceClick()
        )
        Spacer(modifier = Modifier.height(8.dp))


        when (insertState) {
            is Resource.Error -> {
                val errorMessage = (insertState as Resource.Error).message ?: "Unknown error"

                Text(
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 32.dp),
                    color = Color.Red,
                    text = "Gagal Menambahkan Koleksi Tanaman : $errorMessage"
                )
            }

            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(48.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            is Resource.Success -> {
                Text(
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 32.dp),
                    color = Color.Green,
                    text = "Berhasil Mengadopsikan Anabul"
                )
            }

            is Resource.Idle -> {
                // Tidak melakukan apa-apa
            }
        }
    }
}
private enum class PulsateButtonState { Pressed, Idle }
fun Modifier.bounceClick() = composed {
    var buttonState by remember { mutableStateOf(PulsateButtonState.Idle) }
    val scale by animateFloatAsState(if (buttonState == PulsateButtonState.Pressed) 0.90f else 1f)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {  }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == PulsateButtonState.Pressed) {
                    waitForUpOrCancellation()
                    PulsateButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    PulsateButtonState.Pressed
                }
            }
        }
}