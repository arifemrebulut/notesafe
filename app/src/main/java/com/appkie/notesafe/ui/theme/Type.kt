package com.appkie.notesafe.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.appkie.notesafe.R

val Nunito = FontFamily(
    Font(R.font.nunito_regular),
    Font(R.font.nunito_medium, FontWeight.W500),
    Font(R.font.nunito_bold, FontWeight.W700),
    Font(R.font.nunito_semi_bold, FontWeight.W600),
    Font(R.font.nunito_light, FontWeight.W300),
)

val Typography = Typography(
    defaultFontFamily = Nunito
)