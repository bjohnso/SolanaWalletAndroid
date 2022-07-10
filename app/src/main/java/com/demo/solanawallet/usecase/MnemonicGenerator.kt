package com.demo.solanawallet.usecase

import android.content.Context
import com.demo.solanawallet.R
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

object MnemonicGenerator {
    operator fun invoke(context: Context, seed: ByteArray): List<String> {
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val hashedSeed = messageDigest.digest(seed)

        val checksumLength = ((seed.size * 8) / 32) / 8
        val checksum = hashedSeed.take(checksumLength)

        val mnemonicBytes = seed + checksum
        val mnemonicBits = BitSet.valueOf(mnemonicBytes)
        var mnemonicBinaryString = ""

        for (i in 0 until mnemonicBits.length()) {
            mnemonicBinaryString += when (mnemonicBits[i]) {
                true -> 1
                else -> 0
            }
        }

        val mnemonicWordList = context.resources.getStringArray(R.array.mnemonic_word_list)
        val mnemonicSentenceBinaryList = mnemonicBinaryString.chunked(11)

        val mnemonicSentenceDecimalList = mnemonicSentenceBinaryList
            .map { BigInteger(it, 2).toInt() }

        return mnemonicSentenceDecimalList
            .map { mnemonicWordList[it] }
    }
}