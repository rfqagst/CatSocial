package com.example.catsocial.presentation.screen.auth.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
import com.example.catsocial.ui.theme.Neutral60
import com.example.catsocial.util.Resource

@Composable
fun RegisterScreen(modifier: Modifier, navController: NavController, authViewModel: AuthViewModel) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    var showPasswordMismatchDialog by remember { mutableStateOf(false) }

    val isFormValid =
        name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmpassword.isNotEmpty()
    val isPasswordMatching = password == confirmpassword

    val signupFlow = authViewModel.signupFlow.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {

        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = "Registrasi",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 44.sp,
                fontWeight = FontWeight(700),
                color = BlackPrimary,

                )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Segera daftarkan diri anda ke aplikasi! ",
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 27.sp,
                fontWeight = FontWeight(500),
                color = Neutral60,

                )
        )
        Spacer(modifier = Modifier.height(22.dp))
        IconTextField(
            modifier = Modifier,
            titleTextField = "Nama Anda",
            iconTextField = painterResource(
                id = R.drawable.ic_person_input
            ),
            value = name,
            onValueChange = { name = it }
        )
        Spacer(modifier = Modifier.height(22.dp))
        IconTextField(
            modifier = Modifier,
            titleTextField = "Email",
            iconTextField = painterResource(
                id = R.drawable.ic_email
            ),
            value = email,
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordtTextField(
            modifier = Modifier,
            titleTextField = "Password",
            iconTextField = painterResource(
                id = R.drawable.ic_lock
            ),
            value = password,
            onValueChange = { password = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordtTextField(
            modifier = Modifier,
            titleTextField = "Konfirmasi Password",
            iconTextField = painterResource(
                id = R.drawable.ic_lock
            ),
            value = confirmpassword,
            onValueChange = { confirmpassword = it }
        )
        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.height(32.dp))

        LargeBtn(
            text = "Daftar",
            onClick = {
                if (isPasswordMatching) {
                    authViewModel.signUp(name, email, password)
                    Toast.makeText(context, "Berhasil Daftar Akun", Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.Login.route)
                } else {
                    showPasswordMismatchDialog = true
                }
            },
            enabled = isFormValid,
            modifier = Modifier,

            )
        Spacer(modifier = Modifier.height(32.dp))

        Spacer(modifier = Modifier.width(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sudah punya akun?",
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                color = Color(0xFF696B76)
            )
            Spacer(modifier = Modifier.width(2.dp))
            ClickableText(
                modifier = Modifier.padding(top = 2.dp),
                text = AnnotatedString("Masuk"),
                onClick = {
                    navController.navigate(Screen.Login.route)
                },
                style = TextStyle(
                    fontWeight = FontWeight(700),
                    fontSize = 16.sp,

                    color = Color(0xFF696B76),

                    )
            )
        }

    }

    signupFlow.value.let {
        when (it) {
            is Resource.Error -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
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

            is Resource.Idle -> {
                // Do nothing
            }

            null -> {
                // Do nothing
            }
        }
    }

    if (showPasswordMismatchDialog) {
        AlertDialog(
            onDismissRequest = { showPasswordMismatchDialog = false },
            title = { Text("Error") },
            text = { Text("Password dan konfirmasi password tidak cocok") },
            confirmButton = {
                Button(onClick = { showPasswordMismatchDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}