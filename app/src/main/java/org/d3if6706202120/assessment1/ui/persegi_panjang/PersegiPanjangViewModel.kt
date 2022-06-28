package org.d3if6706202120.assessment1.ui.persegi_panjang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if6706202120.assessment1.db.PersegiPanjangDao
import org.d3if6706202120.assessment1.db.PersegiPanjangEntity
import org.d3if6706202120.assessment1.model.HasilPersegiPanjang
import org.d3if6706202120.assessment1.model.hitungPersegiPanjang


class PersegiPanjangViewModel(private val db: PersegiPanjangDao) : ViewModel() {


    private val hasilPersegiPanjang = MutableLiveData<HasilPersegiPanjang?>()


    fun hitung(panjang: Int, lebar: Int) {


        val dataPersegiPanjang = PersegiPanjangEntity(
            panjang = panjang,
            lebar = lebar,
        )
        hasilPersegiPanjang.value = dataPersegiPanjang.hitungPersegiPanjang()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataPersegiPanjang)
            }
        }
    }
    fun getHasilHitung(): LiveData<HasilPersegiPanjang?> = hasilPersegiPanjang

}
