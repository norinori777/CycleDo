package com.google.norinori6791.cycledo.util

import java.text.SimpleDateFormat
import java.util.*

class NowDate {
    fun get(): String{
        val date = Date()
        val format = SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss", Locale.getDefault())

        return format.format(date)
    }
}