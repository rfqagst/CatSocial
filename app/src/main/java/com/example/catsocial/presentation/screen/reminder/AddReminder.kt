package com.example.catsocial.presentation.screen.reminder

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.catsocial.data.room.entity.Reminder
import com.example.catsocial.presentation.components.DateTimeField
import com.example.catsocial.presentation.components.DropdownFieldWithTitle
import com.example.catsocial.presentation.components.LargeBtn
import com.example.catsocial.presentation.components.NormalTextField
import com.example.catsocial.presentation.screen.profile.bounceClick
import com.example.catsocial.util.Resource
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

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

    val clockState = rememberUseCaseState()
    val selectedTime = remember { mutableStateOf(LocalTime.of(0, 0, 0)) }
    var waktuReminderInMillis by remember { mutableStateOf(0L) }

    val insertState by reminderViewModel.insertReminders.collectAsState()

    ClockDialog(
        state = clockState,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            selectedTime.value = LocalTime.of(hours, minutes, 0)
            val currentTime = LocalTime.now()
            val selectedDateTime = if (selectedTime.value.isAfter(currentTime)) {
                selectedTime.value.atDate(LocalDate.now())
            } else {
                selectedTime.value.atDate(LocalDate.now().plusDays(1))
            }
            waktuReminderInMillis = selectedDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        },
        config = ClockConfig(
            defaultTime = selectedTime.value,
            is24HourFormat = true
        )
    )



    Column(modifier = modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(36.dp))
        NormalTextField(
            modifier = Modifier,
            titleTextField = "Nama Reminder",
            value = namaReminder,
            onValueChange = { namaReminder = it },
            placeholderText = "Sarapan Micko, Jadwal Siang"

        )
        Spacer(modifier = Modifier.height(16.dp))
        DropdownFieldWithTitle(
            modifier = Modifier,
            titleTextField = "Reminder Aktif",
            isExpanded = isExpanded,
            selectedValue = selectedValue,
            onExpandedChange = { isExpanded = it },
            items = items,
            onValueChange = { selectedValue = it }

        )
        Spacer(modifier = Modifier.height(16.dp))

        DateTimeField(
            modifier = Modifier,
            titleTextField = "Waktu Pemberian Makan",
            value = waktuReminder,
            onValueChange = { waktuReminder = it },
            onClickIcon = {
                clockState.show()
            },
            icon = Icons.Default.Alarm,
            placeholderText = "10.00, 12.30, 19.30"

        )

        Spacer(modifier = Modifier.height(36.dp))
        LargeBtn(text = "Tambah", onClick = {
            reminderViewModel.insertReminder(
                Reminder(
                    id = 0 ,
                    name = namaReminder,
                    time = waktuReminderInMillis
                )
            )
        }, modifier = Modifier.bounceClick())

        when (insertState) {
            is Resource.Error -> {
                val errorMessage = (insertState as Resource.Error).message ?: "Unknown error"

                Text(
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 32.dp),
                    color = Color.Red,
                    text = "Gagal Menambahkan Koleksi Tanaman : $errorMessage"
                )
            }

            is Resource.Idle -> {
                //
            }

            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(48.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            is Resource.Success -> {
                Text(
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 32.dp),
                    color = Color.Green,
                    text = "Berhasil Menambahkan Reminder makan baru"
                )
            }
        }

    }


}