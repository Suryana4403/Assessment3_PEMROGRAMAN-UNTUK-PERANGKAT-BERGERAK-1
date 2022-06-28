package org.d3if6706202120.assessment1.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "persegipanjang")
data class PersegiPanjangEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var panjang: Int,
    var lebar: Int
)
