package com.google.norinori6791.cycledo.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import java.io.File
import java.io.IOException

class IntentPicture {
    private val requestImageCapture = 1

    fun dispatchTakePictureIntent(activity: Activity, context: Context) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(context.packageManager)?.also {
                activity.startActivityForResult(takePictureIntent, requestImageCapture)
            }
        }
    }
    private fun createOutputFile(context: Context): File? {
        val tempFile = File(context.filesDir, "photos/sample")
        if (!tempFile.exists()) {
            try {
                tempFile.getParentFile().mkdirs()
                tempFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return tempFile
    }
}