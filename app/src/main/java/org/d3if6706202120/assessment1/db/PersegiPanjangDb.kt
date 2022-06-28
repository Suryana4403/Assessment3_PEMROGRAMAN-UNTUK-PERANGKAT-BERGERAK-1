package org.d3if6706202120.assessment1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersegiPanjangEntity::class], version = 1, exportSchema = false)
abstract class PersegiPanjangDb : RoomDatabase() {
    abstract val dao: PersegiPanjangDao
    companion object {
        @Volatile
        private var INSTANCE: PersegiPanjangDb? = null
        fun getInstance(context: Context): PersegiPanjangDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PersegiPanjangDb::class.java,
                        "bmi.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
