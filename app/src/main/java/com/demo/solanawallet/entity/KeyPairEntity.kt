package com.demo.solanawallet.entity

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.TEXT
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Types.BLOB

@Entity(tableName = "tbl_keypairs")
data class KeyPairEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(
        name = "public_key",
        typeAffinity = TEXT
    )
    val publicKey: String,

    @ColumnInfo(
        name = "private_key",
        typeAffinity = BLOB
    )
    val privateKey: ByteArray,

) {

    init {
        require(privateKey.size == PRIVATE_KEY_SIZE) { "Invalid private key length" }
    }

    companion object {
        const val PRIVATE_KEY_SIZE = 32
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KeyPairEntity

        if (id != other.id) return false
        if (!privateKey.contentEquals(other.privateKey)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + privateKey.contentHashCode()
        return result
    }
}