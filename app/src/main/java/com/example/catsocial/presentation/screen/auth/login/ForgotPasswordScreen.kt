package com.example.catsocial.presentation.screen.auth.login

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.catsocial.R
import com.example.catsocial.presentation.components.IconTextField
import com.example.catsocial.presentation.components.LargeBtn
import com.example.catsocial.presentation.navigation.Screen
import com.example.catsocial.presentation.screen.auth.AuthViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassword(modifier: Modifier, navController: NavController, authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lupa Password") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues) // Use paddingValues provided by Scaffold
                .padding(horizontal = 22.dp, vertical = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Silahkan masukkan kembali email yang sudah Anda daftarkan!!",
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 27.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF6B7280),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .width(320.dp)
                    .height(55.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            IconTextField(
                modifier = Modifier,
                titleTextField = "",
                iconTextField = painterResource(id = R.drawable.ic_email),
                value = email,
                onValueChange = {
                    email = it
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            LargeBtn(
                text = "Konfirmasi",
                onClick = { navController.navigate(Screen.Login.route) },
                modifier = Modifier
            )
        }
    }
}