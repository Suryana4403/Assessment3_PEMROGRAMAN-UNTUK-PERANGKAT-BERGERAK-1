package org.d3if6706202120.assessment1.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if6706202120.assessment1.MainActivity
import org.d3if6706202120.assessment1.model.main.BangunDatar
import org.d3if6706202120.assessment1.network.ApiStatus
import org.d3if6706202120.assessment1.network.BangunDatarApi
import org.d3if6706202120.assessment1.network.UpdateWorker
import java.util.concurrent.TimeUnit


class BangunDatarMainViewModel : ViewModel() {
    private val data = MutableLiveData<List<BangunDatar>>()
    private val status = MutableLiveData<ApiStatus>()
    init {
        retrieveData()

    }
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                status.postValue(ApiStatus.SUCCESS)
                data.postValue(BangunDatarApi.service.getBangunDatar())
            } catch (e: Exception) {
                Log.d("BViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }
    fun getData(): LiveData<List<BangunDatar>> = data
    fun getStatus(): LiveData<ApiStatus> = status
    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        
        WorkManager.getInstance(app).enqueueUniqueWork(
            BangunDatarFragment.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

}
