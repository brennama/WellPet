package com.example.wellpet.petcare.petprofiles

import CustomButton
import PetProfile
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
fun PetProfilesScreen(
    onAddPetProfile: () -> Unit,
    onSelectPetProfile: (PetProfile) -> Unit,
    onDeletePetProfile: (String) -> Unit,
    modifier: Modifier = Modifier,
    petProfileViewModel: PetProfileViewModel = hiltViewModel()
) {
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
            if (petProfileViewModel.petProfileList.isNotEmpty()) {
                LazyColumn {
                    itemsIndexed(
                        items = petProfileViewModel.petProfileList,
                        key = { _, petProfile -> petProfile?.petId ?: 0 }
                    ) { index, petProfile ->
                        if (petProfile != null) {
                            PetProfileCard(
                                index,
                                petProfile = petProfile,
                                onDelete = onDeletePetProfile,
                                viewModel = petProfileViewModel
                            )
                        }

                    }
                }
            } else {
                EmptyStateScreen(onAddPetProfile)
            }
            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                text = "+ New profile",
                onClick = onAddPetProfile,
                width = 240.dp
            )
        }
    }
}

@Composable
fun EmptyStateScreen(onAddPetProfile: () -> Unit,
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
            text = "Something’s missing...",
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
            text = "Looks like you don’t have a pet profile. Create one now to unlock budgeting, care planning, and personalization features.",
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
            text = "+ New profile",
            onClick = onAddPetProfile,
            width = 240.dp
        )

    }
}
@Composable
fun PetProfileCard(
    index: Int,
    petProfile: PetProfile,
    onDelete: (String)-> Unit,
    viewModel: PetProfileViewModel
) {
    var expanded by remember { mutableStateOf(false) }

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
                    text = petProfile.petName,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.common_padding))
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        onDelete(petProfile.petId)
                        viewModel.deletePetProfile(petProfile.petId)
                    }
                ) {
                    Icon(Icons.Filled.Delete, stringResource(id = R.string.del_pet_profile))
                }
            }
            if (expanded) {
                Text(text = "Species: ${petProfile.species}")
                Text(text = "Breed: ${petProfile.breed}")
                Text(text = "Temperament: ${petProfile.temperament}")
                Text(text = "Vaccination Status: ${petProfile.vaccinationStatus}")
            }
        }
    }
}