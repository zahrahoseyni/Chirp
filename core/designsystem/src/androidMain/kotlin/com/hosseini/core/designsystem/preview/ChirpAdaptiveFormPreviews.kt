package com.hosseini.core.designsystem.preview

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.hosseini.core.designsystem.components.brand.ChirpBrandLogo
import com.hosseini.core.designsystem.components.layouts.ChirpAdaptiveFormLayout
import com.hosseini.core.designsystem.theme.ChirpTheme

@Composable
@PreviewLightDark
@PreviewScreenSizes
fun ChirpAdaptiveFormPreviews() {
    ChirpTheme {
        ChirpAdaptiveFormLayout(
            headerText = "Welcome to Chirp!",
            errorText = "Failed to login",
            logo = {
                ChirpBrandLogo()
            },
            formContent = {
                Text(
                    text = "Sample form title",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Sample form title 2",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        )
    }
}