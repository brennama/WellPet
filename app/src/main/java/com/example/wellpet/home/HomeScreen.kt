package com.example.wellpet.home

import CustomButton
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wellpet.R
import com.example.wellpet.WellPetScreens.PET_PROFILES_SCREEN
import com.example.wellpet.WellPetScreens.ROUTE_HOME
import com.example.wellpet.WellPetScreens.ROUTE_LOGIN
import com.example.wellpet.auth.ui.AuthViewModel
import com.example.wellpet.ui.theme.WellPetTheme
import com.example.wellpet.ui.theme.spacing

@Composable
fun HomeScreen(viewModel: AuthViewModel?, navController: NavHostController) {
    val spacing = MaterialTheme.spacing
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(spacing.medium)
            .padding(top = spacing.extraLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.welcome_back),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = viewModel?.currentUser?.displayName ?: "",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(spacing.medium)
        ) {




            CustomButton(
                text = stringResource(id = R.string.logout),
                onClick = {
                    viewModel?.logout()
                    navController.navigate(ROUTE_LOGIN) {
                        popUpTo(ROUTE_HOME) { inclusive = true }
                    }
                },
                modifier = Modifier
                        .fillMaxWidth().padding(30.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = spacing.extraLarge)
            )




        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    WellPetTheme {
        HomeScreen(null, rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreviewDark() {
    WellPetTheme {
        HomeScreen(null, rememberNavController())
    }
}