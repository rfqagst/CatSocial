package com.example.catsocial.presentation.screen.adoption

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.catsocial.R
import com.example.catsocial.presentation.components.DescriptionTextField
import com.example.catsocial.presentation.components.DropdownUmur
import com.example.catsocial.presentation.components.LargeBtn
import com.example.catsocial.presentation.components.NormalTextField
import com.example.catsocial.presentation.components.SmallBtn
import com.example.catsocial.ui.theme.GreyPrimary

@Composable
fun AddAdoptionScreen(modifier: Modifier) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    var namaAnabul by remember { mutableStateOf("") }
    var umurAnabul by remember { mutableStateOf("") }
    var beratAnabul by remember { mutableStateOf("") }
    var kelaminAnabul by remember { mutableStateOf("") }
    var deskripsiAnabul by remember { mutableStateOf("") }


    val options = listOf("Tahun", "Bulan")
    var selectedValue by remember { mutableStateOf("Tahun") }
    var expanded by remember { mutableStateOf(false) }


    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
        }

    Column(
        modifier
            .background(GreyPrimary)
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()), // Tambahkan ini untuk scrolling
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(185.dp)
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
            onValueChange = { namaAnabul = it })
        Spacer(modifier = Modifier.height(16.dp))


        Row(modifier = Modifier.fillMaxWidth()) {
            NormalTextField(
                modifier = Modifier.weight(0.7f),
                titleTextField = "Umur Anabul",
                value = umurAnabul,
                onValueChange = { umurAnabul = it })

            Spacer(modifier = Modifier.width(8.dp))

            DropdownUmur(
                modifier = Modifier.weight(0.4f).align(Alignment.Bottom),
                selectedValue = selectedValue,
                isExpanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                },
                items = options,
                onValueChange = {
                    selectedValue = it
                }
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        NormalTextField(
            modifier = Modifier.fillMaxWidth(),
            titleTextField = "Berat Anabul",
            value = beratAnabul,
            onValueChange = { beratAnabul = it })
        Spacer(modifier = Modifier.height(16.dp))
        NormalTextField(
            modifier = Modifier.fillMaxWidth(),
            titleTextField = "Kelamin Anabul",
            value = kelaminAnabul,
            onValueChange = { kelaminAnabul = it })
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionTextField(
            modifier = Modifier.fillMaxWidth(),
            titleTextField = "Deskripsi Anabul",
            value = deskripsiAnabul,
            onValueChange = { deskripsiAnabul = it })

        Spacer(modifier = Modifier.height(38.dp))

        LargeBtn(
            text = "Tambah Koleksi",
            onClick = {
            },
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(38.dp))

    }
}