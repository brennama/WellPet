package com.example.wellpet.auth.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wellpet.R
import com.example.wellpet.ui.theme.WellPetTheme
import com.example.wellpet.ui.theme.spacing

@Composable
fun AuthHeader() {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val spacing = MaterialTheme.spacing

        Image(
            modifier = Modifier
                .size(128.dp, 128.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            painter = painterResource(id = R.drawable.logo_foreground),
            contentDescription = stringResource(id = R.string.app_name)

        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = spacing.medium),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AppHeaderLight() {
    WellPetTheme {
        AuthHeader()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppHeaderDark() {
    WellPetTheme {
        AuthHeader()
    }
}