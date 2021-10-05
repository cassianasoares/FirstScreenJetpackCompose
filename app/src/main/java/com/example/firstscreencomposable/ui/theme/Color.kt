package com.example.firstscreencomposable.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)

val Amber600 = Color(0xFFFFB300)
val Amber800 = Color(0xFFC68400)

val MediumBlack = Color(0xFF121212)

val Colors.chipsSurface: Color
    @Composable
    get() = if(isLight) Purple500 else Color.Black

val Colors.userCardBackground: Color
    @Composable
    get() = if(isLight) Amber600 else MediumBlack

val Colors.chipCardSelectedBackground: Color
    @Composable
    get() = if(isLight) Amber600 else Purple500

val Colors.chipCardBackground: Color
    @Composable
    get() = if(isLight) Purple500 else MediumBlack

val Colors.chipCardStroke: Color
    @Composable
    get() = if(isLight) Purple700 else Color.LightGray

val Colors.deviceCardSelectedStroke: Color
    @Composable
    get() = if(isLight) Purple500 else Purple200