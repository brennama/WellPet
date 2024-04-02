package com.example.wellpet.petcare.data

import android.app.Application
import com.example.wellpet.petcare.petprofileaddedit.AddNewPetProfileViewModel

interface DatabaseRepository {

    fun addPetProfileDetails(addNewPetProfileViewModel: AddNewPetProfileViewModel, application: Application)

    fun deletePetProfile(petProfileId: String)

}