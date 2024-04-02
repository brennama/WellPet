package com.example.wellpet.petcare.petprofileaddedit

import PetProfile
import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.util.Random
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class AddNewPetProfileViewModel @Inject constructor(
    private val application: Application,
    private val auth: FirebaseAuth
) : AndroidViewModel(application = application) {

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    var petName by mutableStateOf("")
    var species by mutableStateOf("")
    var breed by mutableStateOf("")
    var temperament by mutableStateOf("")
    var vaccinationStatus by mutableStateOf("")

    private val random = Random()

    private fun generatePetId(): String {
        val uuid = UUID.randomUUID()
        return uuid.toString()
    }

    fun addPetProfileDetails() {
        currentUser?.let { user ->
            val dB: FirebaseFirestore = FirebaseFirestore.getInstance()
            val dbPetProfiles: CollectionReference = dB.collection("PetProfiles")

            val petId = generatePetId() // Generate random petId

            val petProfiles = PetProfile(
                userId = user.uid,
                petId = petId,
                petName = petName,
                species = species,
                breed = breed,
                temperament = temperament,
                vaccinationStatus = vaccinationStatus,
            )

            dbPetProfiles.add(petProfiles).addOnSuccessListener {
                Toast.makeText(application, "PetProfile added successfully!", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener { e ->
                Toast.makeText(application, "Exception: $e", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            // No user is signed in, handle this case
            Toast.makeText(application, "No user signed in!", Toast.LENGTH_SHORT).show()
        }
    }
}

