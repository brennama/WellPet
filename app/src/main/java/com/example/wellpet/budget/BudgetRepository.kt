package com.example.wellpet.budget

import android.app.Application

interface DatabaseRepository {

    fun addBudgetDetails(addNewBudgetViewModel: AddNewBudgetViewModel, application: Application)

}