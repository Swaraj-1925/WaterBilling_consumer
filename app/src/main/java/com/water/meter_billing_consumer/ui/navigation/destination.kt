package com.water.meter_billing_consumer.ui.navigation

import com.water.meter_billing_consumer.data.Bill
import kotlinx.serialization.Serializable

@Serializable
object ListScreenRoute

@Serializable
data class BillScreenRoute(
    val id: Int,
    val phone: String,
    val reader_id: Int,
    val image_url: String,
    val reading_value: Int,
    val reading_date: String,
    val due_date: String,
    val price: Float

    ,
    val modified: Boolean
)
@Serializable
object SignupScreenRoute
@Serializable
object SigninScreenRoute
