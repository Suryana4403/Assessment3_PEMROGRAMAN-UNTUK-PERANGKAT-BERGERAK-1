package org.d3if6706202120.assessment1.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if6706202120.assessment1.model.main.BangunDatar
import org.d3if6706202120.assessment1.network.BangunDatarApi

class BangunDatarMainViewModel : ViewModel() {
    private val data = MutableLiveData<List<BangunDatar>>()
    init {
        retrieveData()

    }
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                data.postValue(BangunDatarApi.service.getBangunDatar())
            } catch (e: Exception) {
                Log.d("BViewModel", "Failure: ${e.message}")
            }
        }
    }
    fun getData(): LiveData<List<BangunDatar>> = data
}
