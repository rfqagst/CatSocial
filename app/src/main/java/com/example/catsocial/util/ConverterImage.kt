package com.example.catsocial.util

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream
import java.io.InputStream

fun uriToByteArray(context: Context, uri: Uri): ByteArray? {
    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val buffer = ByteArray(1024)
        val byteArrayOutputStream = ByteArrayOutputStream()
        var length: Int

        inputStream?.use {
            while (inputStream.read(buffer).also { length = it } != -1) {
                byteArrayOutputStream.write(buffer, 0, length)
            }
        }

        byteArrayOutputStream.toByteArray()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun byteArrayToImageBitmap(byteArray: ByteArray): ImageBitmap? {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)?.asImageBitmap()
}
