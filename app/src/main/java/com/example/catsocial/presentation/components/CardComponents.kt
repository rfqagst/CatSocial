package com.example.catsocial.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.catsocial.R
import com.example.catsocial.presentation.navigation.Screen
import com.example.catsocial.ui.theme.GreyPrimary
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
    image: Int,
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
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .width(160.dp)
                .height(130.dp)
                .fillMaxWidth()
        )
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text(text = name, fontWeight = FontWeight.SemiBold)
            Text(text = ras, fontWeight = FontWeight.Medium)
        }
    }

}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Preview() {
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        AdoptionCard(
            image = R.drawable.kucing1,
            name = "Albert siwibu",
            ras = "Anggora",
            gender = "Laki-laki",
            usia = "2 bulan",
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(10.dp))
        AdoptionCard(
            image = R.drawable.kucing1,
            name = "Albert siwibu",
            ras = "Anggora",
            gender = "Laki-laki",
            usia = "2 bulan",
            modifier = Modifier
        )
    }


}


