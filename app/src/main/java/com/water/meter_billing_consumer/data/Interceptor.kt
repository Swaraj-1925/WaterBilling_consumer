package com.water.meter_billing_consumer.data


import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
//
//class Interceptor @Inject constructor(
//    private val tokenManager: TokenManager
//) : Interceptor {
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val requestBuilder = chain.request().newBuilder()
//
//        tokenManager.getAccessToken()?.let { accessToken ->
//            requestBuilder.addHeader("Authorization", "Bearer $accessToken")
//        }
//
//        return chain.proceed(requestBuilder.build())
//    }
//}