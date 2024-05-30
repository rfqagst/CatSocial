package com.example.catsocial.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catsocial.ui.theme.OrangePrimary


@Composable
fun SearchBarKucing(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    Column(modifier) {

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            placeholder = {
                Text(text = "Kucing Anggora, Kucing Kampung", fontSize = 14.sp)
            },
            maxLines = 1,
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = OrangePrimary
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp)),
            shape = RoundedCornerShape(10.dp),
            value = value,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OrangePrimary,
                unfocusedBorderColor = Color.Transparent,
            ),
        )
    }

}
