package com.water.meter_billing_consumer.data.services

import com.water.meter_billing_consumer.data.ApiResponse
import com.water.meter_billing_consumer.data.Bill
import com.water.meter_billing_consumer.data.Signin
import com.water.meter_billing_consumer.data.Signup
import com.water.meter_billing_consumer.data.TokenResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface WaterBillingServerService {

    @POST("customers/signup")
    suspend fun signup(
        @Body signupData: Signup
    ): ApiResponse<TokenResponse>

    @POST("customers/login")
    suspend fun login(
        @Body signinData: Signin
    ): ApiResponse<TokenResponse>
    @GET("customers/get_bill")
    suspend fun getAllBills(
        @Header("Authorization") token: String,
        @Query("all_result") allResult: Boolean = true
    ): ApiResponse<List<Bill>>

    @GET("customers/get_bill")
    suspend fun getLatestBill(
        @Header("Authorization") token: String,
        @Query("all_result") allResult: Boolean = false
    ): ApiResponse<Bill>

}

