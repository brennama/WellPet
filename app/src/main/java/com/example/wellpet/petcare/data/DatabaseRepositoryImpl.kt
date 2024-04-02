package com.example.wellpet.petcare.data

import PetProfile
import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import com.example.wellpet.petcare.petprofileaddedit.AddNewPetProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate

class DatabaseRepositoryImpl : DatabaseRepository {
    @SuppressLint("SuspiciousIndentation")
    override fun addPetProfileDetails(addNewPetProfileViewModel: AddNewPetProfileViewModel, application: Application) {

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""

        val dB: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbPetProfiles: CollectionReference = dB.collection("PetProfiles")

        val petProfile = PetProfile(
            userId = userId,
            petId = "",
            petName = "",
            species = "",
            breed = "",
            temperament = "",
            vaccinationStatus = "",

        )

        dbPetProfiles.add(petProfile).addOnSuccessListener {
            Toast.makeText(application, "PetProfile added successfully!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e ->
            Toast.makeText(application, "Exception: $e", Toast.LENGTH_SHORT).show()
        }
    }


        override fun deletePetProfile(petProfileId: String) {
            val dB: FirebaseFirestore = FirebaseFirestore.getInstance()
            val dbPetProfiles: CollectionReference = dB.collection("PetProfiles")

            val docRef = dbPetProfiles.document(petProfileId)


            docRef.delete()
                .addOnSuccessListener {

                }
                .addOnFailureListener { e ->

                }
        }
    }
