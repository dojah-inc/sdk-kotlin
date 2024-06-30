package com.dojah_inc.demoapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.dojah_inc.demoapp.ui.theme.Dojahtest2Theme
import com.dojah_inc.dojah_android_sdk.DojahSdk

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Dojahtest2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    GreetingMain(context = this@MainActivity)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingMain(context: Activity) {
    var widgetIdText by rememberSaveable { mutableStateOf("65ae97f4afee1c0040c9df6a") }
    var refrenceIdText by rememberSaveable { mutableStateOf("") }
    var emailText by rememberSaveable { mutableStateOf("") }
    var idHistory = remember { mutableStateListOf<Pair<String, String>>() }
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.CREATED -> {
                idHistory.clear()
                idHistory.addAll(DojahSdk.with(context).getIdHistory())
            }

            Lifecycle.State.RESUMED -> {
                idHistory.clear()
                idHistory.addAll(DojahSdk.with(context).getIdHistory())
            }
        }
    }
    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = widgetIdText,
                onValueChange = { widgetIdText = it },
                label = { Text("Widget ID") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 16.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
//                    .height(200.dp)
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                item {
                    Text(text = "Widget Id History")
                }

                items(idHistory.size) { index ->
                    val item = idHistory[index]
                    TextButton(onClick = {
                        widgetIdText = item.second
                    }) {
                        Text(text = "${item.first}: ${item.second}")
                    }
                }

            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = refrenceIdText,
                onValueChange = { refrenceIdText = it },
                label = { Text("Reference ID (Optional)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = emailText,
                onValueChange = { emailText = it },
                label = { Text("Email (Optional)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 16.dp)
            )
            LaunchDojahButton {

                if (widgetIdText.isNotBlank()) {
                    DojahSdk.with(context).launch(
                        widgetIdText,
                        referenceId = refrenceIdText.ifBlank { null },
                        email = emailText.ifBlank { null }
                    )
                } else {
                    Toast.makeText(
                        context,
                        "you have to enter a valid Widget ID",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}

@Composable
fun LaunchDojahButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
    ) {
        Text(text = "Launch Dojah SDK")
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Dojahtest2Theme {
        GreetingMain(context = Activity())
    }
}