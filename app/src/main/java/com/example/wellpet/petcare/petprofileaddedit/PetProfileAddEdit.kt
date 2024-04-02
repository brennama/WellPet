package com.example.wellpet.petcare.petprofileaddedit

import CustomButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.wellpet.R

@Composable
fun PetProfileAddEdit(navController: NavHostController, addNewPetProfileViewModel: AddNewPetProfileViewModel = hiltViewModel(), onConfirmProfile: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize().padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(128.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            painter = painterResource(id = R.drawable.logo_foreground),
            contentDescription = "Icon"
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = addNewPetProfileViewModel.petName,
            onValueChange = { addNewPetProfileViewModel.petName = it },
            label = { Text(text = "Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = addNewPetProfileViewModel.species,
            onValueChange = { addNewPetProfileViewModel.species = it },
            label = { Text(text = "Species") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = addNewPetProfileViewModel.breed,
            onValueChange = { addNewPetProfileViewModel.breed = it },
            label = { Text(text = "Breed") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = addNewPetProfileViewModel.temperament,
            onValueChange = { addNewPetProfileViewModel.temperament = it },
            label = { Text(text = "Temperament") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = addNewPetProfileViewModel.vaccinationStatus,
            onValueChange = { addNewPetProfileViewModel.vaccinationStatus = it },
            label = { Text(text = "Vaccination Status") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            text = "Confirm",
            onClick = {
                addNewPetProfileViewModel.addPetProfileDetails()
                onConfirmProfile()
            },
            width = 240.dp
        )
    }
}



