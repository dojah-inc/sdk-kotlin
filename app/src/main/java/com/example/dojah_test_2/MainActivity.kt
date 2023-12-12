package com.example.dojah_test_2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dojah.sdk_kyc.ui.main.MainActivity
import com.dojah.sdk_kyc.ui.splash.SplashActivity
import com.example.dojah_test_2.ui.theme.Dojahtest2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Dojahtest2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    GreetingMain("Test Sandbox", context = this@MainActivity)
                }
            }
        }
    }
}

@Composable
fun GreetingMain(name: String, modifier: Modifier = Modifier, context: Activity) {
    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Greeting("Launch Dojah SDK", context = context, sandbox = false)
        }
    }

}

@Composable
fun Greeting(
    name: String, modifier: Modifier = Modifier, context: Activity, sandbox: Boolean = false
) {
    Button(onClick = {
        print("sandbox extra is : $sandbox")
        context.startActivity(Intent(context, SplashActivity::class.java).apply {
            putExtra("sandbox", sandbox)
        })
    }) {
        Text(text = name)
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Dojahtest2Theme {
        GreetingMain("Android", context = Activity())
    }
}