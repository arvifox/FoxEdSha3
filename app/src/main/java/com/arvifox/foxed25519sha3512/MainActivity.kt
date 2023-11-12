package com.arvifox.foxed25519sha3512

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arvifox.foxed25519sha3512.ui.theme.FoxEd25519Sha3512Theme
import com.arvifox.libed.ed25519.Ed25519Sha3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoxEd25519Sha3512Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val vv = remember { mutableStateOf("") }
    Column {
        Button(onClick = {
            val a = Ed25519Sha3()
            val b = a.generateKeypair()
            vv.value = "pr = ${b.private.algorithm} ${b.private.format}; pu = ${b.public.algorithm} ${b.public.format}"
        }) {
            Text(text = "click here")
        }
        Text(text = vv.value)
    }
}
