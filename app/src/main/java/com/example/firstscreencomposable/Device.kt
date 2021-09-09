package com.example.firstscreencomposable

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Device(val icon: Int, val name: String, var status: MutableState<Boolean>,
        val category:String)

val deviceList = arrayListOf(
    Device(R.drawable.ic_computer, "Computer", mutableStateOf(true), "Gamer"),
    Device(R.drawable.ic_led, "Led", mutableStateOf(true), "Gamer"),
    Device(R.drawable.ic_air, "Air Cond.", mutableStateOf(false), "Gamer"),
    Device(R.drawable.ic_videocam, "WebCam", mutableStateOf(true), "Work"),
    Device(R.drawable.ic_computer, "Computer", mutableStateOf(true), "Work"),
    Device(R.drawable.ic_video_label, "Computer", mutableStateOf(true), "Movie"),
    Device(R.drawable.ic_air, "Air Cond.", mutableStateOf(true), "Movie")
)
