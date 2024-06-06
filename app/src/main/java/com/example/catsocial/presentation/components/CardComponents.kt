package com.example.catsocial.presentation.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.catsocial.R
import com.example.catsocial.presentation.navigation.Screen
import com.example.catsocial.ui.theme.BlackPrimary
import com.example.catsocial.ui.theme.OrangePrimary
import com.example.catsocial.ui.theme.YellowBanner

@Composable
fun BannerCard(modifier: Modifier, navController: NavHostController) {

    Row(
        modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(YellowBanner),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Temukan Keluarga baru \n Untuk Anabul mu",
                lineHeight = 23.sp,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            SmallBtn(modifier = Modifier, text = "Adopsikan", onClick = {
                navController.navigate(Screen.AdoptionAdd.route)
            })
        }
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            alignment = Alignment.CenterEnd,
            painter = painterResource(id = R.drawable.elgato),
            contentDescription = null
        )
    }
}

@Composable
fun AdoptionCard(
    image: ImageBitmap,
    name: String,
    ras: String,
    gender: String,
    usia: String,
    modifier: Modifier
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(10.dp)
            .wrapContentSize()
    ) {
        Image(
            bitmap = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .width(160.dp)
                .height(130.dp)
                .fillMaxWidth()
        )
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text(text = name, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Text(text = ras, fontWeight = FontWeight.Medium, fontSize = 16.sp)
            Row(modifier = Modifier.padding(top = 6.dp)) {
                FilterCard(modifier = Modifier, text = gender)
                FilterCard(modifier = Modifier, text = usia)
            }
        }
    }
}


@Composable
fun FilterCard(modifier: Modifier, text: String) {
    Box(
        modifier = Modifier.padding(end = 6.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(OrangePrimary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = text,
            style = TextStyle(
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

    }
}


@Composable
fun CatInformationCard(modifier: Modifier, name: String, description: String, image: String) {
    Row(
        modifier = modifier
            .padding(bottom = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .width(60.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(YellowBanner)
        ) {


            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = rememberAsyncImagePainter(model = image),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(600),
                    color = BlackPrimary,
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                text = description,
                color = BlackPrimary,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                )
            )
            Spacer(modifier = Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

            }
        }

    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Preview() {


}



