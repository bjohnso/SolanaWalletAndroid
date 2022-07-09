package com.demo.solanawallet.repository
import android.content.Context
import com.demo.solanawallet.database.SolanaDatabase
import com.demo.solanawallet.entity.KeyPairEntity
import com.demo.solanawallet.extensions.ContextExtensions.solanaWalletApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bouncycastle.crypto.generators.Ed25519KeyPairGenerator
import org.bouncycastle.crypto.params.Ed25519KeyGenerationParameters
import java.security.SecureRandom

class SolanaRepository(context: Context) {
    private var db: SolanaDatabase = SolanaDatabase(context.solanaWalletApplication())

    suspend fun generateKeyPair() {
        val keyPairGenerator = Ed25519KeyPairGenerator().apply {
            init(Ed25519KeyGenerationParameters(SecureRandom()))
        }

        val keyPairEntity = KeyPairEntity.createFromAsymmetricCipherKeyPair(
            keyPairGenerator.generateKeyPair()
        )

        persistKeyPair(keyPairEntity)
    }

    suspend fun persistKeyPair(vararg keyPair: KeyPairEntity) =
        withContext(Dispatchers.IO) {
            db.keyPairDao().persistKeyPairs(*keyPair)
        }

    companion object {
        private var instance: SolanaRepository? = null

        fun getInstance(context: Context): SolanaRepository {
            return instance ?: SolanaRepository(context).also {
                instance = it
            }
        }
    }
}