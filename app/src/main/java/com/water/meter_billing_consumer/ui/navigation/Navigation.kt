package com.water.meter_billing_consumer.ui.navigation

import BillScreen
import ListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.water.meter_billing_consumer.data.Bill
import com.water.meter_billing_consumer.ui.screens.SignInScreen
import com.water.meter_billing_consumer.ui.screens.SignUpScreen
import okhttp3.Route
import kotlin.Int

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    startDestination: Any,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<ListScreenRoute> {
            ListScreen(navController = navController)
        }
        composable<SignupScreenRoute> {
            SignUpScreen(navController = navController)
        }
        composable<SigninScreenRoute> {
            SignInScreen(navController = navController)
        }
        composable<BillScreenRoute> {
            val bill = it.toRoute<BillScreenRoute>()
            val data = Bill(
            id=bill.id,
            phone=bill.phone,
            reader_id=bill.reader_id,
            image_url=bill.image_url,
            reading_value=bill.reading_value,
            reading_date=bill.reading_date,
            due_date=bill.due_date,
            price=bill.price,
            modified=bill.modified
            )
            BillScreen(bill = data)
        }

    }
}