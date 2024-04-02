package com.example.wellpet.petcare.petprofiles

import PetProfile
import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel


class PetProfileViewModel @Inject constructor(private val application: Application) : AndroidViewModel(application = application) {

    var petProfileList = mutableStateListOf<PetProfile?>()
    var myDatabase: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val userId = currentUser?.uid ?: ""

    init {
        getPetProfileDetails()
    }

    fun getPetProfileDetails() {
        myDatabase.collection("PetProfiles").whereEqualTo("userId", userId).get()
            .addOnSuccessListener { mySnapshot ->
                petProfileList.clear() // Clear existing list
                if (!mySnapshot.isEmpty) {
                    val list = mySnapshot.documents
                    for (items in list) {
                        val myPetProfile: PetProfile? = items.toObject(PetProfile::class.java)
                        petProfileList.add(myPetProfile)
                    }
                } else {
                    Toast.makeText(
                        application,
                        "No data found in Database",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.addOnFailureListener {
                Toast.makeText(
                    application,
                    "Failed to get the data.",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun refreshPetProfiles() {
        getPetProfileDetails()
    }

    fun deletePetProfile(petProfileId: String) {
        viewModelScope.launch {
            deletePetProfile(petProfileId,

            )
        }
    }
}