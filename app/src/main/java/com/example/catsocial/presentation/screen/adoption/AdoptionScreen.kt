package com.example.catsocial.presentation.screen.adoption

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.catsocial.R
import com.example.catsocial.presentation.components.AdoptionCard
import com.example.catsocial.presentation.components.BannerCard


@Composable
fun AdoptionScreen(modifier: Modifier, navController: NavHostController) {
    Column(modifier.padding(16.dp)) {
        BannerCard(
            modifier = Modifier.padding(top = 16.dp, bottom = 26.dp),
            navController = navController
        )
        Text(text = "Mulai Adopsi Anabul", fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(6) { index ->
                AdoptionCard(
                    image = R.drawable.kucing1,
                    name = "Rizal Hitam Manis",
                    ras = "Anggora",
                    gender = "Non Binary",
                    usia = "6 Bulan",
                    modifier = Modifier
                )
            }
        }

    }
}