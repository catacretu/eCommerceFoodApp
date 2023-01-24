package com.example.ecommercefoodapp.data.local.model
import java.io.Serializable

data class OrderEntity (
    val orderID: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val address: String,
    val city: String,
    val country: String,
    val delivery: String
): Serializable