package com.example.catsocial.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.catsocial.presentation.components.LargeBtn
import com.example.catsocial.presentation.components.NormalTextFieldGreen
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.*
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun EditProfileScreen (modifier: Modifier){
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    Column (modifier.padding(start = 22.dp, end = 22.dp, top = 80.dp)){
        NormalTextFieldGreen(modifier = Modifier,
            titleTextField ="Nama" ,
            value = name,
            onValueChange = {name = it})
        Spacer(modifier = Modifier.height(32.dp))
        NormalTextFieldGreen(modifier = Modifier,
            titleTextField ="Email" ,
            value =email,
            onValueChange = {email = it})
        Spacer(modifier = Modifier.height(60.dp))
        LargeBtn(text = "Simpan",
            onClick = { /*TODO*/ },
            modifier = Modifier
        )
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
