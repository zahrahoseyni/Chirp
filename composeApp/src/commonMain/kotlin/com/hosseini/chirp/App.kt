package com.hosseini.chirp

import androidx.compose.runtime.*
import com.hosseini.core.designsystem.theme.ChirpTheme
import com.hosseini.feature.auth.presenation.register.RegisterRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ChirpTheme {
        RegisterRoot()
    }
}
