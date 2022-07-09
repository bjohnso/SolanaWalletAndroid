package com.demo.solanawallet.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.demo.solanawallet.dao.KeyPairDao
import com.demo.solanawallet.entity.KeyPairEntity

@Database(
    entities = [KeyPairEntity::class],
    version = 1
)
abstract class SolanaDatabase: RoomDatabase() {
    abstract fun keyPairDao(): KeyPairDao

    companion object {
        @Volatile private var instance: SolanaDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context = context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            databaseBuilder(context, SolanaDatabase::class.java, "solana.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}