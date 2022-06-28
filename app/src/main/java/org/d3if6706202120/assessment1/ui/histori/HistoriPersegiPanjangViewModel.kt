package org.d3if6706202120.assessment1.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if6706202120.assessment1.db.PersegiPanjangDao

class HistoriPersegiPanjangViewModel(private val db: PersegiPanjangDao) : ViewModel() {
    val data = db.getLastPersegiPanjang()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}