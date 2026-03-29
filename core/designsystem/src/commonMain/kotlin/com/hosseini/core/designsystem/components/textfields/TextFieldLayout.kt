package com.hosseini.core.designsystem.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun TextFieldLayout(
    definition: TextFieldDefinition,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = definition.title,
            color = definition.titleColor,
            fontSize = definition.titleFontSize
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(definition.cornerRadius))
                .background(definition.backgroundColor)
                .border(
                    width = definition.borderWidth,
                    color = definition.borderColor,
                    shape = RoundedCornerShape(definition.cornerRadius)
                )
                .padding(
                    horizontal = definition.horizontalPadding,
                    vertical = definition.verticalPadding
                )
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = definition.singleLine,
                textStyle = definition.textStyle,
                keyboardOptions = definition.keyboardOptions,
                visualTransformation = definition.visualTransformation,
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = definition.placeHolder,
                            color = definition.placeHolderColor,
                            fontSize = definition.placeHolderFontSize
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

data class TextFieldDefinition(
    val title: String,
    val placeHolder: String,
    val backgroundColor: Color = Color.White,
    val borderColor: Color = Color(0xFFCCCCCC),
    val borderWidth: Dp = 1.dp,
    val cornerRadius: Dp = 12.dp,
    val horizontalPadding: Dp = 12.dp,
    val verticalPadding: Dp = 14.dp,
    val titleColor: Color = Color(0xFF222222),
    val placeHolderColor: Color = Color(0xFF888888),
    val titleFontSize: androidx.compose.ui.unit.TextUnit = 14.sp,
    val placeHolderFontSize: androidx.compose.ui.unit.TextUnit = 14.sp,
    val textStyle: TextStyle = TextStyle(
        color = Color(0xFF111111),
        fontSize = 14.sp
    ),
    val singleLine: Boolean = true,
    val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    val visualTransformation: VisualTransformation = VisualTransformation.None
)
