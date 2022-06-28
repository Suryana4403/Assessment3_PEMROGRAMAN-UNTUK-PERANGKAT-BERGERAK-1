package org.d3if6706202120.assessment1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersegiPanjangDao {
    @Insert
    fun insert(persegipanjang: PersegiPanjangEntity)

    @Query("SELECT * FROM persegipanjang ORDER BY id DESC")
    fun getLastPersegiPanjang(): LiveData<List<PersegiPanjangEntity>>

    @Query("DELETE FROM persegipanjang")
    fun clearData()
}