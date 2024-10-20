package com.example.labtesttask.presentation.components

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.labtesttask.R

object FontFamily {
    val customFontFamily = FontFamily(
        Font(resId = R.font.nunito_black, weight = FontWeight.Black),
        Font(resId = R.font.nunito_bold, weight = FontWeight.Bold)
    )
}