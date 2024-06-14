package com.example.catsocial.presentation.screen.adoption

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.navigation.NavHostController
import com.example.catsocial.R
import com.example.catsocial.presentation.components.SmallBtn
import com.example.catsocial.presentation.navigation.Screen

@Composable
fun AdoptionConfirmationScreen(modifier: Modifier, navController: NavHostController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Permintaan Adopsi Berhasil",
            fontSize = 24.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF1DE154),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 48.dp, bottom = 48.dp)
        )


        Image(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(174.dp)
                .height(174.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "meowmeomeomeomemwmwmeowmeowmeomwme ( Anda akan dihubungi Majikan sebelumnya untuk informasi lebih lanjut. ) \n",
            fontSize = 18.sp,
            lineHeight = 20.96.sp,
            fontWeight = FontWeight(400),
            fontStyle = FontStyle.Italic,
            color = Color(0xFF141916),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(48.dp))


        SmallBtn(
            text = "Kembali",
            onClick = { navController.navigate(Screen.Adoption.route) },
            modifier = Modifier
        )

    }
}