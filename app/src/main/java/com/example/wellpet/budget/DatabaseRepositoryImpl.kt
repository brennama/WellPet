package com.example.wellpet.budget

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class DatabaseRepositoryImpl : DatabaseRepository {
    @SuppressLint("SuspiciousIndentation")
    override fun addBudgetDetails(addNewBudgetViewModel: AddNewBudgetViewModel, application: Application) {

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""

        val dB: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbBudgets: CollectionReference = dB.collection("Budgets")

        val budgets = Budget(
            userId = userId,
            budgetName = addNewBudgetViewModel.budgetName,
            annualVetVisitAmount = addNewBudgetViewModel.annualVetVisitAmount.toFloatOrNull() ?: 0f,
            vaccinationsAmount = addNewBudgetViewModel.vaccinationsAmount.toFloatOrNull() ?: 0f,
            foodAmount = addNewBudgetViewModel.foodAmount.toFloatOrNull() ?: 0f,
            preventativesAmount = addNewBudgetViewModel.preventativesAmount.toFloatOrNull() ?: 0f,
            groomingAmount = addNewBudgetViewModel.groomingAmount.toFloatOrNull() ?: 0f,
            treatsAmount = addNewBudgetViewModel.treatsAmount.toFloatOrNull() ?: 0f,
            toysAmount = addNewBudgetViewModel.toysAmount.toFloatOrNull() ?: 0f,

        )

        dbBudgets.add(budgets).addOnSuccessListener {
            Toast.makeText(application, "Budget added successfully!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e ->
            Toast.makeText(application, "Exception: $e", Toast.LENGTH_SHORT).show()
        }
    }
}