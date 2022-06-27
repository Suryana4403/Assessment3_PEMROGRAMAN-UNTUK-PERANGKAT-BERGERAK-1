package org.d3if6706202120.assessment1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if6706202120.assessment1.model.HasilPersegiPanjang


class MainViewModel  : ViewModel() {

    private val hasilHitung = MutableLiveData<HasilPersegiPanjang?>()

    fun hitung(panjang: Int, lebar: Int) {
        val keliling = (2 * (panjang + lebar))
        val luas = panjang * lebar
        hasilHitung.value = HasilPersegiPanjang(keliling, luas)

    }
    fun getHasilHitung(): LiveData<HasilPersegiPanjang?> = hasilHitung
}
