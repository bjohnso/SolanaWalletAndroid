package com.demo.solanawallet.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import com.demo.solanawallet.SolanaWalletApplication

object ContextExtensions {
    fun Context.solanaWalletApplication(): SolanaWalletApplication =
        applicationContext as SolanaWalletApplication

    tailrec fun Context.activity(): Activity? = when (this) {
        is Activity -> this
        else -> (this as? ContextWrapper)?.baseContext?.activity()
    }
}