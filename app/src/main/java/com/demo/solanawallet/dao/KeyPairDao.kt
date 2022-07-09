package com.demo.solanawallet.dao

import androidx.room.*

@Dao
interface KeyPairDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun persistKeyPairs(vararg keyPairDao: KeyPairDao)

    @Transaction
    @Delete
    fun destroyKeyPairs(vararg keyPairDao: KeyPairDao)

    @Transaction
    @Query("delete from tbl_keypairs")
    fun destroyAllKeyPairs()
}