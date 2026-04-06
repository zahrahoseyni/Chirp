package com.hosseini.core.designsystem.components.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hosseini.core.designsystem.components.brand.ChirpSuccessIcon
import com.hosseini.core.designsystem.components.buttons.ChirpButton
import com.hosseini.core.designsystem.components.buttons.ChirpButtonStyle
import com.hosseini.core.designsystem.theme.ChirpTheme
import com.hosseini.core.designsystem.theme.extended
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ChirpSimpleSuccessLayout(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    icon: @Composable () -> Unit,
    primaryButton: @Composable () -> Unit,
    secondaryButton: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon()
        Column(
            modifier = modifier
                .fillMaxWidth()
                .offset(y = (-25).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.extended.textPrimary,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.extended.textSecondary,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(24.dp))

            primaryButton()

            if (secondaryButton != null) {
                Spacer(modifier = Modifier.height(8.dp))
                secondaryButton()
            }
        }
    }

}

@Composable
@Preview(showBackground = false)
fun ChirpSimpleSuccessLayoutPreview() {
    ChirpTheme(darkTheme = true) {
        ChirpSimpleSuccessLayout(
            title = "Chirp successfully created!",
            description = "We’ve sent verification email to olivia@chirp.chat",
            icon = { ChirpSuccessIcon() },
            primaryButton = {
                ChirpButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Log in",
                    onClick = {}
                )
            },
            secondaryButton = {
                ChirpButton(
                    text = "Resend verification email",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    style = ChirpButtonStyle.SECONDARY
                )
            }
        )
    }
}