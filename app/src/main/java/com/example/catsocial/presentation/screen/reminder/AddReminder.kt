package com.example.catsocial.presentation.screen.reminder

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.catsocial.presentation.components.DropdownFieldWithTitle
import com.example.catsocial.presentation.components.LargeBtn
import com.example.catsocial.presentation.components.NormalTextField
import com.example.catsocial.presentation.navigation.Screen
import com.example.catsocial.ui.theme.OrangePrimary

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminder(
    modifier: Modifier,
    reminderViewModel: ReminderViewModel,
    navController: NavController
) {
    var namaReminder by remember {
        mutableStateOf("")
    }
    var reminderAktif by remember {
        mutableStateOf("")
    }
    var waktuReminder by remember {
        mutableStateOf("")
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedValue by remember {
        mutableStateOf("")
    }
    val items = listOf("Hari ini", "Setiap Hari")

    Column (modifier = modifier.padding(30.dp)){
        Spacer(modifier = Modifier.height(50.dp))
        NormalTextField(
            modifier = Modifier,
            titleTextField = "Nama Reminder",
            value = namaReminder,
            onValueChange = { namaReminder = it })
        Spacer(modifier = Modifier.height(16.dp))
        DropdownFieldWithTitle(
            modifier = Modifier,
            titleTextField = "Reminder Aktif",
            isExpanded = isExpanded,
            selectedValue = selectedValue,
            onExpandedChange =  { isExpanded = it },
            items = items,
            onValueChange = { selectedValue = it }

        )
        Spacer(modifier = Modifier.height(16.dp))
        NormalTextField(
            modifier = Modifier,
            titleTextField = "Waktu Reminder",
            value = waktuReminder,
            onValueChange = { waktuReminder = it })
        
        Spacer(modifier = Modifier.height(16.dp))
        LargeBtn(text = "Tambah", onClick = { /*TODO*/ }, modifier = Modifier.bounceClick())
    }
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            onClick = { navController.navigate(Screen.AdoptionAdd.route) },
            containerColor = OrangePrimary,
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "")
        }

    }

}

private enum class PulsateButtonState { Pressed, Idle }
fun Modifier.bounceClick() = composed {
    var buttonState by remember { mutableStateOf(PulsateButtonState.Idle) }
    val scale by animateFloatAsState(if (buttonState == PulsateButtonState.Pressed) 0.90f else 1f)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {  }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == PulsateButtonState.Pressed) {
                    waitForUpOrCancellation()
                    PulsateButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    PulsateButtonState.Pressed
                }
            }
        }
}