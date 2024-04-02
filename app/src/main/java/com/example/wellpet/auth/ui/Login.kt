package com.example.wellpet.auth.ui

import CustomButton
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wellpet.R
import com.example.wellpet.WellPetScreens.ROUTE_HOME
import com.example.wellpet.WellPetScreens.ROUTE_LOGIN
import com.example.wellpet.WellPetScreens.ROUTE_SIGNUP
import com.example.wellpet.auth.data.Resource
import com.example.wellpet.ui.theme.WellPetTheme
import com.example.wellpet.ui.theme.spacing


@Composable
fun LoginScreen(viewModel: AuthViewModel?, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginFlow = viewModel?.loginFlow?.collectAsState()

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (refHeader, refEmail, refPassword, refButtonLogin, refTextSignup, refLoader) = createRefs()
        val spacing = MaterialTheme.spacing

        Box(
            modifier = Modifier
                .constrainAs(refHeader) {
                    top.linkTo(parent.top, spacing.extraLarge)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .wrapContentSize()
        ) {
            AuthHeader()
        }



        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            modifier = Modifier.constrainAs(refEmail) {
                top.linkTo(refHeader.bottom, spacing.extraLarge)
                start.linkTo(parent.start, spacing.large)
                end.linkTo(parent.end, spacing.large)
                width = Dimension.fillToConstraints
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = stringResource(id = R.string.password))
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.constrainAs(refPassword) {
                top.linkTo(refEmail.bottom, spacing.medium)
                start.linkTo(parent.start, spacing.large)
                end.linkTo(parent.end, spacing.large)
                width = Dimension.fillToConstraints
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )



        CustomButton(
            text = stringResource(id = R.string.login),
            onClick = {
                viewModel?.login(email, password)
            },
            modifier = Modifier
                .fillMaxWidth().padding(30.dp)
                .constrainAs(refButtonLogin) {
                    top.linkTo(refPassword.bottom, spacing.large)
                    start.linkTo(parent.start, spacing.large)
                    end.linkTo(parent.end, spacing.large)
                },
        )



        Text(
            modifier = Modifier
                .constrainAs(refTextSignup) {
                    top.linkTo(refButtonLogin.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.extraLarge)
                    end.linkTo(parent.end, spacing.extraLarge)
                }
                .clickable {
                    navController.navigate(ROUTE_SIGNUP) {
                        popUpTo(ROUTE_LOGIN) { inclusive = true }
                    }
                },
            text = stringResource(id = R.string.dont_have_account),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.onSurface
        )

        loginFlow?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.constrainAs(refLoader) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                }
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_LOGIN) { inclusive = true }
                        }
                    }
                }

                else -> {}
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun LoginScreenPreviewLight() {
    WellPetTheme {
        LoginScreen(null, rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreviewDark() {
    WellPetTheme {
        LoginScreen(null, rememberNavController())
    }
}
