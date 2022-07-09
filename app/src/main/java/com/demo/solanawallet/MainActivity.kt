package com.demo.solanawallet

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.demo.solanawallet.extensions.ContextExtensions.activity
import com.demo.solanawallet.ui.theme.SolanaWalletTheme
import com.demo.solanawallet.viewmodel.SolanaViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolanaWalletTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { MainScreen(this) }
            }
        }
    }
}

@Composable
fun MainScreen(context: Context) {
    val viewModel = (context.activity() as? ComponentActivity)
        ?.let { ViewModelProvider(it )[SolanaViewModel::class.java] }

    val coroutineScope = rememberCoroutineScope()

    Text(
        text = "Solana Wallet",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        style = TextStyle(fontSize = 24.sp, color = MaterialTheme.colors.primary)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
//        Text(
//            text = publicKey ?: "",
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 16.dp, bottom = 16.dp),
//            style = TextStyle(fontSize = 24.sp, color = MaterialTheme.colors.primary)
//        )

        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel?.createWallet(context = context)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp)
                .aspectRatio(5f)
        ) {
            Text(text = "Generate Keypair")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SolanaWalletTheme {}
}