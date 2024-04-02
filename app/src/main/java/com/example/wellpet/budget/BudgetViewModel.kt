package com.example.wellpet.budget

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class BudgetViewModel @Inject constructor(private val application: Application) : AndroidViewModel(application = application) {

    var budgetList = mutableStateListOf<Budget?>()
    var myDatabase: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val userId = currentUser?.uid ?: ""

    init {
        getBudgetDetails()
    }

    fun getBudgetDetails() {
        myDatabase.collection("Budgets").whereEqualTo("userId", userId).get()
            .addOnSuccessListener { mySnapshot ->
                budgetList.clear() // Clear existing list
                if (!mySnapshot.isEmpty) {
                    val list = mySnapshot.documents
                    for (items in list) {
                        val myBudget: Budget? = items.toObject(Budget::class.java)
                        budgetList.add(myBudget)
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

}