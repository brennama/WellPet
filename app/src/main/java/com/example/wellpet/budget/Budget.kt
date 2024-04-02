package com.example.wellpet.budget

data class Budget(
    val userId: String,
    val budgetName: String,
    val annualVetVisitAmount: Float,
    val vaccinationsAmount: Float,
    val foodAmount: Float,
    val preventativesAmount: Float,
    val groomingAmount: Float,
    val treatsAmount: Float,
    val toysAmount: Float,
) {
    constructor() : this(
        "",
        "",
        0f,
        0f,
        0f,
        0f,
        0f,
        0f,
        0f,
    )
}