package com.demo.solanawallet.viewmodel

import androidx.lifecycle.ViewModel
import com.demo.solanawallet.repository.SolanaRepository

class SolanaViewModel: ViewModel() {
    val publicKey = SolanaRepository.instance.publicKey
}