package com.example.wellpet.budget

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
import javax.inject.Inject

@HiltViewModel
class AddNewBudgetViewModel @Inject constructor(
    private val application: Application,
    private val auth: FirebaseAuth
) : AndroidViewModel(application = application) {

    val currentUser: FirebaseUser?
        get() = auth.currentUser


    var budgetName by  mutableStateOf("")
    var annualVetVisitAmount by  mutableStateOf("")
    var vaccinationsAmount by  mutableStateOf("")
    var foodAmount by  mutableStateOf("")
    var preventativesAmount by  mutableStateOf("")
    var groomingAmount by  mutableStateOf("")
    var treatsAmount by  mutableStateOf("")
    var toysAmount by  mutableStateOf("")

    fun addBudgetDetails() {
        currentUser?.let { user ->
            val dB: FirebaseFirestore = FirebaseFirestore.getInstance()
            val dbBudgets: CollectionReference = dB.collection("Budgets")

            val budgets = Budget(
                userId = user.uid,
                budgetName = budgetName,
                annualVetVisitAmount = annualVetVisitAmount.toFloatOrNull() ?: 0f,
                vaccinationsAmount = vaccinationsAmount.toFloatOrNull() ?: 0f,
                foodAmount = foodAmount.toFloatOrNull() ?: 0f,
                preventativesAmount = preventativesAmount.toFloatOrNull() ?: 0f,
                groomingAmount = groomingAmount.toFloatOrNull() ?: 0f,
                treatsAmount = treatsAmount.toFloatOrNull() ?: 0f,
                toysAmount = toysAmount.toFloatOrNull() ?: 0f,
            )

            dbBudgets.add(budgets).addOnSuccessListener {
                Toast.makeText(application, "Budget added successfully!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { e ->
                Toast.makeText(application, "Exception: $e", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            // No user is signed in, handle this case
            Toast.makeText(application, "No user signed in!", Toast.LENGTH_SHORT).show()
        }
    }
}