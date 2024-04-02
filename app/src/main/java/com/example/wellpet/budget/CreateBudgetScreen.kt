package com.example.wellpet.budget

import CustomButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.wellpet.R

@Composable
fun CreatePetBudgetScreen(navController: NavHostController, addNewBudgetViewModel: AddNewBudgetViewModel = hiltViewModel(), onConfirmBudget: () -> Unit) {

    val homeViewModel: BudgetViewModel = hiltViewModel()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().padding(30.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                painter = painterResource(id = R.drawable.logo_foreground, ),
                contentDescription = "Icon"

            )
            Spacer(modifier = Modifier.height(16.dp))

            AddNewBudgetOutlinedTextWithLabel(
                value = addNewBudgetViewModel.budgetName,
                onValueChanged = { addNewBudgetViewModel.budgetName = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                label = "Name")

            Spacer(modifier = Modifier.height(16.dp))

            AddNewBudgetOutlinedTextWithLabel(
                value = addNewBudgetViewModel.annualVetVisitAmount,
                onValueChanged = {
                    addNewBudgetViewModel.annualVetVisitAmount =
                        (it.toFloatOrNull() ?: 0f).toString()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = "Annual Vet Visit")


            Spacer(modifier = Modifier.height(16.dp))

            AddNewBudgetOutlinedTextWithLabel(
                value = addNewBudgetViewModel.vaccinationsAmount,
                onValueChanged = {
                    addNewBudgetViewModel.vaccinationsAmount =
                        (it.toFloatOrNull() ?: 0f).toString()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = "Vaccinations")


            Spacer(modifier = Modifier.height(16.dp))

            AddNewBudgetOutlinedTextWithLabel(
                value = addNewBudgetViewModel.foodAmount,
                onValueChanged = {
                    addNewBudgetViewModel.foodAmount =
                        (it.toFloatOrNull() ?: 0f).toString()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = "Food")


            Spacer(modifier = Modifier.height(16.dp))

            AddNewBudgetOutlinedTextWithLabel(
                value = addNewBudgetViewModel.preventativesAmount,
                onValueChanged = {
                    addNewBudgetViewModel.preventativesAmount =
                        (it.toFloatOrNull() ?: 0f).toString()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = "Preventatives")


            Spacer(modifier = Modifier.height(16.dp))

            AddNewBudgetOutlinedTextWithLabel(
                value = addNewBudgetViewModel.groomingAmount,
                onValueChanged = {
                    addNewBudgetViewModel.groomingAmount =
                        (it.toFloatOrNull() ?: 0f).toString()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = "Grooming")


            Spacer(modifier = Modifier.height(16.dp))

            AddNewBudgetOutlinedTextWithLabel(
                value = addNewBudgetViewModel.treatsAmount,
                onValueChanged = {
                    addNewBudgetViewModel.treatsAmount =
                        (it.toFloatOrNull() ?: 0f).toString()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = "Treats"
                )

            Spacer(modifier = Modifier.height(16.dp))

            AddNewBudgetOutlinedTextWithLabel(
                value = addNewBudgetViewModel.toysAmount,
                onValueChanged = {
                    addNewBudgetViewModel.toysAmount =
                        (it.toFloatOrNull() ?: 0f).toString()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = "Toys")


            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                text = "Confirm",
                onClick = {
                    addNewBudgetViewModel.addBudgetDetails()
                    homeViewModel.getBudgetDetails()
                    onConfirmBudget()
                },
                width = 136.dp
            )
        }
    }
}



@Composable
fun AddNewBudgetOutlinedTextWithLabel(
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    label: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f) // Fill available space
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .width(if (label == "Name") 200.dp else 125.dp)
                .padding(end = 16.dp),
            placeholder = {if (label == "Name") null else Text(text = "\$xxx.xx") }
        )
    }
}