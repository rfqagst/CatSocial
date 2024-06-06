package com.example.catsocial.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun NormalTextField(
    modifier: Modifier,
    titleTextField: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = titleTextField,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(700),
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White),
            value = value,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OrangePrimary,
                unfocusedBorderColor = Color.Transparent,
            ),
        )
    }
}

@Composable
fun DescriptionTextField(
    modifier: Modifier,
    titleTextField: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = titleTextField,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(700),
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .height(100.dp),
            value = value,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OrangePrimary,
                unfocusedBorderColor = Color.Transparent,
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownFieldWithTitle(
    modifier: Modifier,
    titleTextField : String,
    isExpanded: Boolean,
    selectedValue: String,
    onExpandedChange: (Boolean) -> Unit,
    items: List<String>,
    onValueChange: (String) -> Unit,
) {

    Column {
        Text(
            text = titleTextField,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(700),
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        ExposedDropdownMenuBox(
            modifier = modifier,
            expanded = isExpanded,
            onExpandedChange = onExpandedChange,
        ) {
            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .menuAnchor()
                    .fillMaxWidth()
                ,
                value = selectedValue,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = OrangePrimary
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = OrangePrimary,
                    unfocusedBorderColor = Color.Transparent,
                )
            )

            ExposedDropdownMenu(
                modifier = modifier,
                expanded = isExpanded,
                onDismissRequest = { onExpandedChange(false) }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            onValueChange(item)
                            onExpandedChange(false)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    modifier: Modifier,
    isExpanded: Boolean,
    selectedValue: String,
    onExpandedChange: (Boolean) -> Unit,
    items: List<String>,
    onValueChange: (String) -> Unit,
) {

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = onExpandedChange,
    ) {
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .menuAnchor()
            ,
            value = selectedValue,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = OrangePrimary
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OrangePrimary,
                unfocusedBorderColor = Color.Transparent,
            )
        )

        ExposedDropdownMenu(
            modifier = modifier,
            expanded = isExpanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onValueChange(item)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}





