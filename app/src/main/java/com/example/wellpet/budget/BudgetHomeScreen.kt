package com.example.wellpet.budget

import CustomButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wellpet.R

@Composable
fun PetBudgetScreen(onAddBudget: () -> Unit,
                    onSelectBudget: (Budget) -> Unit,
                    modifier: Modifier = Modifier,
                    homeViewModel: BudgetViewModel = hiltViewModel()) {
    
        Column {
            Text(
                text = stringResource(id = R.string.app_header),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.common_padding)),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            HorizontalDivider(modifier = Modifier)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.common_padding))
            ) {
                if (homeViewModel.budgetList.isNotEmpty()) {
                    LazyColumn {
                        itemsIndexed(
                            items = homeViewModel.budgetList,
                            key = { _, budget -> budget?.budgetName ?: 0 }
                        ) { index, budget ->
                            if (budget != null) {
                                BudgetCard(
                                    index,
                                    budget = budget,
                                )
                            }

                        }
                    }
                } else {
                    EmptyStateScreen(onAddBudget)
                }
                Spacer(modifier = Modifier.height(16.dp))

                CustomButton(
                    text = "+ New budget",
                    onClick = onAddBudget,
                    width = 240.dp
                )
            }
        }
    }

    @Composable
    fun EmptyStateScreen(onAddBudget: () -> Unit,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(128.dp, 128.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                painter = painterResource(id = R.drawable.logo_foreground),
                contentDescription = stringResource(id = R.string.app_name)

            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Oops! There’s nothing here :(",
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF4D4C4B),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Looks like you haven’t set up a pet care budget yet.",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF706F6E),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                text = "+ New budget",
                onClick = onAddBudget,
                width = 240.dp
            )

        }
    }
@Composable
fun BudgetCard(
    index: Int,
    budget: Budget,
) {
    var expanded by remember { mutableStateOf(false) } // State to manage expansion

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.common_padding))
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded } // Toggle expansion
            ) {
                Text(
                    text = (index+1).toString(),
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.common_padding))
                )
                Text(
                    text = budget.budgetName,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.common_padding))
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                    }
                ) {
                    Icon(Icons.Filled.Delete, stringResource(id = R.string.del_pet_profile))
                }
            }
            if (expanded) {
                // Show additional fields
                Column(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    Text("Annual Vet Visit Amount: ${budget.annualVetVisitAmount}")
                    Text("Vaccinations Amount: ${budget.vaccinationsAmount}")
                    Text("Food Amount: ${budget.foodAmount}")
                    Text("Preventatives Amount: ${budget.preventativesAmount}")
                    Text("Grooming Amount: ${budget.groomingAmount}")
                    Text("Treats Amount: ${budget.treatsAmount}")
                    Text("Toys Amount: ${budget.toysAmount}")
                }
            }
        }
    }
}