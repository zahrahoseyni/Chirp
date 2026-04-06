package com.hosseini.feature.auth.presenation.register

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import chirp.feature.auth.presentation.generated.resources.Res
import chirp.feature.auth.presentation.generated.resources.email
import chirp.feature.auth.presentation.generated.resources.email_placeholder
import chirp.feature.auth.presentation.generated.resources.login
import chirp.feature.auth.presentation.generated.resources.password
import chirp.feature.auth.presentation.generated.resources.password_hint
import chirp.feature.auth.presentation.generated.resources.register
import chirp.feature.auth.presentation.generated.resources.username
import chirp.feature.auth.presentation.generated.resources.username_hint
import chirp.feature.auth.presentation.generated.resources.username_placeholder
import chirp.feature.auth.presentation.generated.resources.welcome_to_chirp
import com.hosseini.core.designsystem.components.brand.ChirpBrandLogo
import com.hosseini.core.designsystem.components.buttons.ChirpButton
import com.hosseini.core.designsystem.components.buttons.ChirpButtonStyle
import com.hosseini.core.designsystem.components.layouts.ChirpAdaptiveFormLayout
import com.hosseini.core.designsystem.components.layouts.ChirpSnackbarScaffold
import com.hosseini.core.designsystem.components.textfield.ChirpPasswordTextField
import com.hosseini.core.designsystem.components.textfield.ChirpTextField
import com.hosseini.core.designsystem.theme.ChirpTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterRoot(
    viewModel: RegisterViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    RegisterScreen(
        state = state,
        onAction = viewModel::onAction,
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    ChirpSnackbarScaffold(
        snackbarHostState = snackbarHostState
    ) {
        ChirpAdaptiveFormLayout(
            headerText = stringResource(Res.string.welcome_to_chirp),
            errorText = state.registrationError?.asString(),
            logo = { ChirpBrandLogo() },
            formContent = {
                Spacer(modifier = Modifier.height(16.dp))

                ChirpTextField(
                    state = state.usernameTextState,
                    placeholder = stringResource(Res.string.username_placeholder),
                    title = stringResource(Res.string.username),
                    supportingText = state.usernameError?.asString()
                        ?: stringResource(Res.string.username_hint),
                    isError = !state.isUsernameValid,
                    onFocusChanged = { onAction(RegisterAction.OnInputTextFocusGain) })

                Spacer(modifier = Modifier.height(16.dp))
                ChirpTextField(
                    state = state.emailTextState,
                    placeholder = stringResource(Res.string.email_placeholder),
                    title = stringResource(Res.string.email),
                    supportingText = state.emailError?.asString(),
                    isError = !state.isEmailValid,
                    onFocusChanged = { onAction(RegisterAction.OnInputTextFocusGain) })

                Spacer(modifier = Modifier.height(16.dp))
                ChirpPasswordTextField(
                    state = state.passwordTextState,
                    placeholder = stringResource(Res.string.password),
                    title = stringResource(Res.string.password),
                    supportingText = state.passwordError?.asString()
                        ?: stringResource(Res.string.password_hint),
                    isError = !state.isPasswordValid,
                    isPasswordVisible = state.isPasswordVisible,
                    onToggleVisibilityClick = { onAction(RegisterAction.OnTogglePasswordVisibility) },
                    onFocusChanged = { onAction(RegisterAction.OnInputTextFocusGain) })

                Spacer(modifier = Modifier.height(16.dp))

                ChirpButton(
                    text = stringResource(Res.string.register),
                    onClick = { onAction(RegisterAction.OnRegisterClick) },
                    enabled = state.canRegister,
                    isLoading = state.isRegistering,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                ChirpButton(
                    text = stringResource(Res.string.login),
                    onClick = { onAction(RegisterAction.OnLoginClick) },
                    style = ChirpButtonStyle.SECONDARY,
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
        RegisterScreen(
            state = RegisterState(),
            onAction = {},
            snackbarHostState = remember { SnackbarHostState() }
        )
    }
}