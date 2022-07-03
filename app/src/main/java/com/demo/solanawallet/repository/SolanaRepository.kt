package com.demo.solanawallet.repository
import com.demo.solanawallet.usecase.Base58EncodeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import org.bouncycastle.crypto.generators.Ed25519KeyPairGenerator
import org.bouncycastle.crypto.params.Ed25519KeyGenerationParameters
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters
import java.security.SecureRandom

class SolanaRepository {
    val publicKey = MutableStateFlow<String?>(null)

    fun generateKeyPair() {
        val keyPairGenerator = Ed25519KeyPairGenerator().apply {
            init(Ed25519KeyGenerationParameters(SecureRandom()))
        }

        val keypair = keyPairGenerator.generateKeyPair()
        val publicKey = (keypair.public as Ed25519PublicKeyParameters).encoded

        this.publicKey.value = Base58EncodeUseCase.invoke(publicKey)
    }

    companion object {
        val instance = SolanaRepository()
    }
}