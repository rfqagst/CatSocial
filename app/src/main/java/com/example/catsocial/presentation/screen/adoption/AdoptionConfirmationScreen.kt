package com.example.catsocial.presentation.screen.adoption

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catsocial.R

@Composable
fun AdoptionConfirmationScreen(modifier: Modifier) {

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {

    }
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    // Child views.

    {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .size(100.dp)
                .width(800.dp)
                .height(800.dp)
                .padding(top = 0.dp, bottom = 1.dp)

            // .align(Alignment.CenterHorizontally)
        )
        {

            Text(
                text = "Permintaan Adopsi Berhasil",
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 35.94.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF1DE154),
                    textAlign = TextAlign.Center,

                    ),
                modifier = Modifier
                    .padding(start = 58.dp, end = 59.dp, top = 146.dp, bottom = 600.dp)
                    .width(243.dp)
                    .height(980.dp)
                    .align(Alignment.Center)


            )
            Box(
                modifier = Modifier
                    .width(174.dp)
                    .height(174.dp)
                    .align(Alignment.Center)
            )
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,

                modifier = Modifier
                    .width(174.dp)
                    .height(174.dp)
                    .align(Alignment.Center)
            )

            Text(
                text = "meowmeomeomeomemwmwmeowmeowmeomwme ( Anda akan dihubungi Majikan sebelumnya untuk informasi lebih lanjut. ) \n",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.96.sp,
                    fontWeight = FontWeight(400),
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFF141916),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .width(360.dp)
                    .height(800.dp)
                    .padding(start = 37.dp, end = 37.dp, top = 495.dp, bottom = 214.dp)
                    .align(Alignment.Center)

            )

            Text(
                text = "Kembali",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF1DE154),
                ),
                modifier = Modifier
                    .width(800.dp)
                    .height(800.dp)
                    .align(Alignment.Center)
                    .padding(top = 614.dp, start = 153.dp, end = 131.dp, bottom = 170.dp)

            )

            //    Icon(painter = "", contentDescription = "kembali")



        }
    }
}