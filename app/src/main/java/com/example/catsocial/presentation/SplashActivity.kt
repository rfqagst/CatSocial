package com.example.catsocial.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.catsocial.R
import com.example.catsocial.presentation.screen.auth.AuthViewModel
import com.example.catsocial.ui.theme.CatSocialTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authViewModel: AuthViewModel by viewModels()
        setContent {
            CatSocialTheme {
                SplashScreen()
            }
        }
    }
}

@Composable
private fun SplashScreen() {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        delay(1000)
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        (context as? SplashActivity)?.finish()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .width(360.dp)
                    .height(800.dp)
                    .background(color = Color(0xFFF4F4F4))
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "CatSocial",
                    style = TextStyle(
                        fontSize = 48.sp,
                        lineHeight = 71.87.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier
                        .padding(start = 62.dp, end = 66.dp, top = 355.dp, bottom = 343.dp)
                        .width(232.dp)
                        .height(72.dp)
                        .align(Alignment.Center)
                )
                Image(
                    painter = painterResource(id = R.drawable.kucing),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(39.dp)
                        .height(39.dp)
                        .align(Alignment.Center)
                        .padding(end = 6.dp, start = 2.dp)
                )
            }
        }
    }
}