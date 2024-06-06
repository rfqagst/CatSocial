package com.example.catsocial.util

import android.content.Context
import android.net.Uri
import android.util.Base64
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.io.InputStream

class ConvertersImage {
    @TypeConverter
    fun fromByteArray(value: ByteArray): String {
        return Base64.encodeToString(value, Base64.DEFAULT)
    }

    @TypeConverter
    fun toByteArray(value: String): ByteArray {
        return Base64.decode(value, Base64.DEFAULT)
    }
}

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