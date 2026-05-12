package com.hosseini.feature.auth.presenation.register_success

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import chirp.feature.auth.presentation.generated.resources.Res
import chirp.feature.auth.presentation.generated.resources.account_successfully_created
import chirp.feature.auth.presentation.generated.resources.login
import chirp.feature.auth.presentation.generated.resources.resend_verification_email
import chirp.feature.auth.presentation.generated.resources.verification_email_sent_to_x
import com.hosseini.core.designsystem.components.brand.ChirpSuccessIcon
import com.hosseini.core.designsystem.components.buttons.ChirpButton
import com.hosseini.core.designsystem.components.buttons.ChirpButtonStyle
import com.hosseini.core.designsystem.components.layouts.ChirpAdaptiveResultLayout
import com.hosseini.core.designsystem.components.layouts.ChirpSimpleSuccessLayout
import com.hosseini.core.designsystem.theme.ChirpTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterSuccessRoot(
    viewModel: RegisterSuccessViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RegisterSuccessScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun RegisterSuccessScreen(
    state: RegisterSuccessState,
    onAction: (RegisterSuccessAction) -> Unit,
) {
    ChirpAdaptiveResultLayout {
        ChirpSimpleSuccessLayout(
            title = stringResource(Res.string.account_successfully_created),
            description = stringResource(
                Res.string.verification_email_sent_to_x,
                state.registeredEmail
            ),
            icon = { ChirpSuccessIcon() },
            primaryButton = {
                ChirpButton(
                    text = stringResource(Res.string.login),
                    onClick = { onAction(RegisterSuccessAction.OnLoginClick) },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            secondaryButton = {
                ChirpButton(
                    text = stringResource(Res.string.resend_verification_email),
                    onClick = { onAction(RegisterSuccessAction.OnResendVerificationEmailClick) },
                    style = ChirpButtonStyle.SECONDARY,
                    enabled = !state.isResendingVerificationEmail,
                    isLoading = state.isResendingVerificationEmail,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ChirpTheme {
        RegisterSuccessScreen(
            state = RegisterSuccessState(registeredEmail = "zahra@test.com"),
            onAction = {}
        )
    }
}