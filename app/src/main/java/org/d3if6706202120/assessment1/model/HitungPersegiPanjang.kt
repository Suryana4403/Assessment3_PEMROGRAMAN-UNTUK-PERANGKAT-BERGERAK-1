package org.d3if6706202120.assessment1.model


import org.d3if6706202120.assessment1.db.PersegiPanjangEntity

fun PersegiPanjangEntity.hitungPersegiPanjang(): HasilPersegiPanjang {
    val keliling = (2 * (panjang + lebar))
    val luas = panjang * lebar
    return HasilPersegiPanjang(keliling, luas)
}
