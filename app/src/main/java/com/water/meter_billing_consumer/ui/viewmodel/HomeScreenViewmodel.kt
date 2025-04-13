package com.water.meter_billing_consumer.ui.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.net.Uri
import com.water.meter_billing_consumer.data.Bill
import com.water.meter_billing_consumer.data.WaterBillingServerRepository

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: WaterBillingServerRepository
) : ViewModel() {
    private val _phoneNumber = MutableStateFlow<String>("")
    val phoneNumber = _phoneNumber.asStateFlow()
    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl = _imageUrl.asStateFlow()
    private val _extractedReading = MutableStateFlow<String?>(null)
    val extractedReading = _extractedReading.asStateFlow()

    private val _bill = MutableStateFlow< List<Bill?>>(emptyList())
    val bill = _bill.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()


    fun onPhoneNumberChange(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }
    fun getBills(allBills: Boolean=true){
        viewModelScope.launch {
            try {
                if (allBills) {
                    val response = repository.getAllBills()
                    if (response!!.status == "success" && response.data != null) {
                        _bill.value = response.data
                    } else {
                        _error.value = response.error ?: "Unknown error"
                    }
                } else {
                    val response = repository.getLatestBill()
                    if (response!!.status == "success" && response.data != null) {
                        _bill.value = listOf(response.data)
                    } else {
                        _error.value = response.error ?: "Unknown error"
                    }
                }
            } catch (e: Exception) {
                Log.e("HomeScreenViewModel", "Error fetching bills", e)
                _error.value = "Something went wrong"
            }
        }

    }
    fun logout(){
        viewModelScope.launch {
            repository.logout()
            Log.d("HomeScreenViewModel", ":logout -m Logged out")
        }
    }
}