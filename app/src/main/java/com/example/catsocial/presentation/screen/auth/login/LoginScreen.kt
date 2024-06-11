package com.example.catsocial.presentation.screen.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.catsocial.R
import com.example.catsocial.presentation.components.IconTextField
import com.example.catsocial.presentation.components.LargeBtn
import com.example.catsocial.presentation.components.PasswordtTextField
import com.example.catsocial.presentation.navigation.Screen
import com.example.catsocial.presentation.screen.auth.AuthViewModel
import com.example.catsocial.ui.theme.BlackPrimary
import com.example.catsocial.ui.theme.GreyPrimary
import com.example.catsocial.ui.theme.Neutral60
import com.example.catsocial.ui.theme.OrangePrimary
import com.example.catsocial.ui.theme.PrimaryLight
import com.example.catsocial.util.Resource

@Composable
fun LoginScreen(
    modifier: Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    var rememberMe by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginFlow = authViewModel.loginFlow.collectAsState()
    var showErrorDialog by remember { mutableStateOf(false) }

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = { Text("Login Gagal") },
            text = { Text("Email atau Password Salah") },
            confirmButton = {
                Button(onClick = { showErrorDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    if (authViewModel.isLoggedIn()) {
        LaunchedEffect(Unit) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Home.route) {
                    inclusive = true
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 34.dp)
    ) {
        Text(
            text = "Masuk",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 44.sp,
                fontWeight = FontWeight(700),


                )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Selamat datang kembali!",
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 27.sp,
                fontWeight = FontWeight(500),
                color = Neutral60,

                )
        )
        Spacer(modifier = Modifier.height(22.dp))
        IconTextField(
            modifier = Modifier, titleTextField = "Email",
            iconTextField = painterResource(
                id = R.drawable.ic_email
            ),
            value = email,
            onValueChange = { email = it },
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordtTextField(
            modifier = Modifier, titleTextField = "Password",
            iconTextField = painterResource(
                id = R.drawable.ic_lock
            ),
            value = password,
            onValueChange = { password = it },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                modifier = Modifier
                    .padding(0.dp)
                    .offset(-12.dp),
                checked = rememberMe,
                onCheckedChange = { rememberMe = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = OrangePrimary,
                    checkmarkColor = Color.White
                ),
            )
            Text(
                text = "Ingat Saya",
                modifier = Modifier
                    .weight(1f)
                    .offset(x = -16.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(500),
                    color = BlackPrimary,

                    )
            )
            ClickableText(
                text = AnnotatedString("Lupa Password?"),
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(500),
                    color = Neutral60,
                    textAlign = TextAlign.Right,
                ),


                ) {
                navController.navigate(Screen.ForgotPassword.route)
            }

        }
        Spacer(modifier = Modifier.height(32.dp))
        LargeBtn(text = "Masuk", onClick = {
            authViewModel.login(email, password)
        }, modifier = Modifier)

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Atau",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF9CA3AF),

                    ),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "",
                modifier = Modifier
                    .padding(1.dp)
                    .width(41.dp)
                    .height(42.dp)
                    .clickable { }
                    .background(PrimaryLight, shape = RoundedCornerShape(30.dp))
                    .clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = "",
                modifier = Modifier
                    .padding(1.dp)
                    .width(41.dp)
                    .height(42.dp)
                    .clickable { }
                    .background(PrimaryLight, shape = RoundedCornerShape(30.dp))
                    .clip(shape = CircleShape)
            )
        }
        Spacer(modifier = Modifier.defaultMinSize(minHeight = 34.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Belum punya akun?",
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                color = Color(0xFF696B76)
            )
            Spacer(modifier = Modifier.width(2.dp))
            ClickableText(
                modifier = Modifier.padding(top = 2.dp),
                text = AnnotatedString("Daftar"),
                onClick = {
                    navController.navigate(Screen.Register.route)
                },
                style = TextStyle(
                    fontWeight = FontWeight(700),
                    fontSize = 16.sp,

                    color = Color(0xFF696B76),

                    )
            )
        }
    }

    loginFlow.value.let {
        when(it) {
            is Resource.Error -> {
                showErrorDialog= true
                authViewModel.clearLoginFlow()

            }
            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                }
            }
            null -> { /* Do nothing */ }
            is Resource.Idle -> {
                // Do nothing
            }
        }
    }
}

