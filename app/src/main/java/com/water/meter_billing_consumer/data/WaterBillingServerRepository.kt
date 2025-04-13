package com.water.meter_billing_consumer.data

import android.util.Log
import com.water.meter_billing_consumer.data.services.WaterBillingServerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WaterBillingServerRepository @Inject constructor(
    private val service: WaterBillingServerService,
    private val tokenManager: TokenManager
){
    suspend fun signUp(
        name: String,
        email: String,
        phone: String,
        password: String,
        address: String
    ): Int{
        try {
            Log.d("WaterBillingServerRepository : SignUp", "name: $name, email: $email, phone: $phone, password: $password, address: $address")
            val signupData = Signup(
                name = name,
                email = email,
                phone = phone,
                password = password,
                address = address
            )
            val response = service.signup(signupData)
//            Log.d("WaterBillingServerRepository : SignUp", "Response: $response")
            val token = response.data?.token
            if (token != null){
                tokenManager.saveAccessToken(token)
                Log.d("WaterBillingServerRepository : SignUp", "Token Saved $token")

                return 200
            }else{
                Log.e("WaterBillingServerRepository : SignUp", "Token is null")
                return 500
            }
        }catch (e: Exception) {
            Log.e("WaterBillingServerRepository : SignUp", "Error: ${e.message}")
            return 404
        }
    }
    suspend fun signIn(phone: String, password: String): Int{
        try {
            val signinData = Signin(
                phone = phone,
                password = password
            )

            val response =  service.login(signinData)
            Log.d("WaterBillingServerRepository : SignIn", "Response: $response")
            return 200
        }catch (e: Exception) {
            Log.e("WaterBillingServerRepository : SignIn", "Error: ${e.message}")
            return 404
        }
    }
    fun isAuthenticated(): Boolean{
        Log.d("WaterBillingServerRepository : isAuthenticated", "Token: ${tokenManager.getAccessToken()}")
        return tokenManager.getAccessToken() != null
    }

    suspend fun logout() {
        withContext(Dispatchers.IO) {
            tokenManager.clearToken()
        }
    }
    suspend fun getLatestBill(): ApiResponse<Bill>? {
        try {


            val token = "Bearer ${tokenManager.getAccessToken()}"
            val res: ApiResponse<Bill> = service.getLatestBill(
                token = token,
            )
            Log.d("WaterBillingServerRepository : getAllBills", "Response: $res")
            return res
        }catch (e: Exception){
            Log.e("WaterBillingServerRepository : getMeterReading", "Error: ${e.message}")
            return null
        }
    }
    suspend fun getAllBills(): ApiResponse<List<Bill>>? {
        try {
            val token = "Bearer ${tokenManager.getAccessToken()}"
            val res: ApiResponse<List<Bill>> = service.getAllBills(token = token)
            Log.d("WaterBillingServerRepository : getAllBills", "Response: $res")
            return res
        }catch (e: Exception){
            Log.e("WaterBillingServerRepository : getMeterReading", "Error: ${e.message}")
            return null
        }
    }
}

