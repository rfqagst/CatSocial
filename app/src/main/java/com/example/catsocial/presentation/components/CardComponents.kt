package com.example.catsocial.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.*
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun BannerCard(modifier: Modifier, navController: NavHostController) {
    var isHovered by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (isHovered) 1.05f else 1f)

    Row(
        modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(YellowBanner)
            .pointerInput(Unit) {
                coroutineScope {
                    launch {
                        awaitPointerEventScope {
                            while (true) {
                                val event = awaitPointerEvent()
                                isHovered = event.changes.any { it.pressed }
                            }
                        }
                    }
                }
            }
            .graphicsLayer(scaleX = scale, scaleY = scale),
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
    modifier: Modifier,
    imageModifier: Modifier,
    textModifier: Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(10.dp)
            .wrapContentSize()
    ) {
        Image(
            bitmap = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = imageModifier
                .clip(RoundedCornerShape(10.dp))
                .width(160.dp)
                .height(130.dp)
                .fillMaxWidth()
        )
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = textModifier
            )
            Text(text = ras, fontWeight = FontWeight.Medium, fontSize = 16.sp)
            Row(modifier = Modifier.padding(top = 6.dp)) {
                CategoryChip(modifier = Modifier, text = gender)
                CategoryChip(modifier = Modifier, text = usia)
            }
        }
    }
}


@Composable
fun CategoryChip(modifier: Modifier, text: String) {
    Box(
        modifier = modifier
            .padding(end = 6.dp)
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
fun ReminderCard(
    modifier: Modifier,
    reminderName: String,
    reminderTime: String,
    editOnclick: () -> Unit
) {

    Column(
        modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = reminderName)
            Text(
                text = "Edit",
                color = OrangePrimary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    editOnclick()
                })
        }
        Column(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Makan Selanjutnya", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = reminderTime,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = OrangePrimary
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))

}



