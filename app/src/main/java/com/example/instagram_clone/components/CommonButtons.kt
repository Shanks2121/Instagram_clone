package com.example.instagram_clone.components



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp

import com.example.instagram_clone.ui.theme.Dimension



@Composable
fun DrawableButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primary,
    iconTint: Color,
    onButtonClicked: () -> Unit,
    painter: Painter,
    shape: Shape = MaterialTheme.shapes.medium,
    iconSize: Dp = Dimension.mdIcon.times(0.8f),
    elevation: Dp = Dimension.zero,
    paddingValue: PaddingValues = PaddingValues(Dimension.xs),
) {
    Box(
        modifier = modifier
            .shadow(elevation = elevation, shape = shape)
            .clip(shape)
            .background(backgroundColor)
            .clickable {
                onButtonClicked()
            }
            .padding(paddingValues = paddingValue)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(iconSize),
            painter = painter,
            contentDescription = "icon next",
            tint = iconTint,
        )
    }
}







@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    elevationEnabled: Boolean = true,
    buttonColor: Color,
    contentColor: Color,
    padding: PaddingValues = PaddingValues(vertical = Dimension.sm, horizontal = Dimension.sm),
    shape: Shape = MaterialTheme.shapes.medium,
    borderStroke: BorderStroke = BorderStroke(width = Dimension.zero, color = Color.Transparent),
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.button,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    onButtonClicked: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        border = borderStroke,
        onClick = {
            onButtonClicked()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor,
            contentColor = contentColor,
            disabledBackgroundColor = buttonColor.copy(alpha = 0.7f),
            disabledContentColor = contentColor,
        ),
        enabled = enabled,
        shape = shape,
        contentPadding = padding,
        elevation = if (elevationEnabled) ButtonDefaults.elevation()
        else ButtonDefaults.elevation(
            defaultElevation = Dimension.zero,
            pressedElevation = Dimension.zero
        ),
    ) {
        leadingIcon()
        Text(
            modifier = Modifier.padding(horizontal = Dimension.pagePadding.div(2)),
            text = text,
            textAlign = TextAlign.Center,
            style = textStyle,
            color = contentColor,
        )

        trailingIcon()
    }
}


