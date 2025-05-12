package com.dojah.demoapp

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.dojah.demoapp.ui.theme.Dojahtest2Theme
import com.dojah.demoapp.ui.widget.DojahDropDownInputField
import com.dojah.kyc_sdk_kotlin.DOJAH_RESULT_KEY
import com.dojah.kyc_sdk_kotlin.DOJAH_APPROVED_RESULT
import com.dojah.kyc_sdk_kotlin.DOJAH_FAILED_RESULT
import com.dojah.kyc_sdk_kotlin.DOJAH_PENDING_RESULT
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.domain.BusinessData
import com.dojah.kyc_sdk_kotlin.domain.ExtraUserData
import com.dojah.kyc_sdk_kotlin.domain.GovData
import com.dojah.kyc_sdk_kotlin.domain.GovId
import com.dojah.kyc_sdk_kotlin.domain.Location
import com.dojah.kyc_sdk_kotlin.domain.UserData
import timber.log.Timber
import java.util.logging.Logger

class MainActivity : ComponentActivity() {


    private val dojahResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult: ActivityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                activityResult.data?.getStringExtra(DOJAH_RESULT_KEY)?.let {
                    when(it){
                        DOJAH_APPROVED_RESULT -> Toast.makeText(this, "Approved!", Toast.LENGTH_SHORT).show()
                        DOJAH_PENDING_RESULT -> Toast.makeText(this, "Pending!", Toast.LENGTH_SHORT).show()
                        DOJAH_FAILED_RESULT -> Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                    }
                    Toast.makeText(this, "Got Result: $it", Toast.LENGTH_SHORT).show()
                }
            }
        }

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

private const val TEXT_INPUT_HEIGHT = 75

private val govDataOptions = listOf("dl", "nin", "bvn", "nin")
private val govIdOptions = listOf("national", "passport", "dl", "voter", "nin", "others")
private val businessOptions = listOf("cac")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingMain(context: Activity) {
    var widgetIdText by rememberSaveable { mutableStateOf("65ae97f4afee1c0040c9df6a") }
    var refrenceIdText by rememberSaveable { mutableStateOf("") }
    var emailText by rememberSaveable { mutableStateOf("") }
    var extraFirstNameText by rememberSaveable { mutableStateOf("Abdul") }
    var extraLastNameText by rememberSaveable { mutableStateOf("Ola") }
    //  dd-mm-yyyy
    var extraDobText by rememberSaveable { mutableStateOf("02-03-1991") }
    var extraEmailText by rememberSaveable { mutableStateOf("s@sail.com") }
    var govDataOption by rememberSaveable { mutableStateOf(govDataOptions.first() to "323323232323") }
    var govIdOption by rememberSaveable { mutableStateOf(govIdOptions.first() to "https://nairametrics.com/wp-content/uploads/2013/11/nigeria-national-identity-smart-cards-combine-id-and-mastercard.jpg") }
    var businessOption by rememberSaveable { mutableStateOf(businessOptions.first() to "3081661") }
    var govDataText by rememberSaveable { mutableStateOf(govDataOption.second) }
    var govIdText by rememberSaveable { mutableStateOf(govIdOption.second) }
    var addressText by rememberSaveable { mutableStateOf("") }
    var businessText by rememberSaveable { mutableStateOf(businessOption.second) }
    var latitudeText by rememberSaveable { mutableStateOf("40.71") }
    var longitudeText by rememberSaveable { mutableStateOf(" 74.01") }
    var idHistory = remember { mutableStateListOf<Pair<String, String>>() }
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    val dojahResultLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
                print("Got Result ok: $it")
            it.data?.getStringExtra(DOJAH_RESULT_KEY)?.let {
                when(it){
                    DOJAH_APPROVED_RESULT -> Toast.makeText(context, "Approved!", Toast.LENGTH_SHORT).show()
                    DOJAH_PENDING_RESULT -> Toast.makeText(context, "Pending!", Toast.LENGTH_SHORT).show()
                    DOJAH_FAILED_RESULT -> Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show()
                }
                print("Got Result: $it")
                Toast.makeText(context, "Got Result: $it", Toast.LENGTH_SHORT).show()
            }
        }
    }

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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            item {
                Spacer(Modifier.height(100.dp))

                OutlinedTextField(
                    value = widgetIdText,
                    onValueChange = { widgetIdText = it },
                    label = { Text("Widget ID") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TEXT_INPUT_HEIGHT.dp)
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp)
                )
            }
            item {
                Text(
                    text = "Widget Id History",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp),
                )
            }

            items(idHistory.size) { index ->
                val item = idHistory[index]
                TextButton(
                    onClick = {
                        widgetIdText = item.second
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterStart),
                ) {
                    Text(
                        text = "${item.first}: ${item.second}",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            item {

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
                        .height(TEXT_INPUT_HEIGHT.dp)
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = emailText,
                    onValueChange = { emailText = it },
                    label = { Text("Email (Optional)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TEXT_INPUT_HEIGHT.dp)
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp)
                )
            }

            item {
                //GovId
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = govIdText,
                        onValueChange = {
                            govIdText = it
                            govIdOption = govIdOption.first to it
                        },
                        label = { Text("Gov ID:Image (Optional)") },
                        modifier = Modifier
                            .weight(1f)
                            .height(TEXT_INPUT_HEIGHT.dp)
                            .padding(start = 32.dp)
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(Modifier.width(12.dp))
                    DojahDropDownInputField(
                        labelText = govIdOption.first,
                        options = govIdOptions,
                        indicatorColor = Color.Black,
                        containerColor = Color.Transparent,
//                    borderWidth = 1.dp,
                        dropDownColor = Color.Gray,
                        columnModifier = Modifier
                            .weight(0.5f)
                            .height((TEXT_INPUT_HEIGHT * 0.88).dp)
                            .padding(end = 29.dp)
                            .padding(bottom = 12.dp, top = 3.dp)
                            .align(Alignment.CenterVertically),
                        textFieldModifier = Modifier,
                        borderShape = RoundedCornerShape(4.dp),
                        onValueChange = { selection ->
                            govIdOption = selection to govIdText

                        }
                    )

                }
            }

            item {
                //GovData
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = govDataText,
                        onValueChange = {
                            govDataText = it
                            govDataOption = govIdOption.first to it
                        },
                        label = { Text("Gov Data (Optional)") },
                        modifier = Modifier
                            .weight(1f)
                            .height(TEXT_INPUT_HEIGHT.dp)
                            .padding(start = 32.dp)
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(Modifier.width(12.dp))
                    DojahDropDownInputField(
                        labelText = govDataOption.first,
                        options = govDataOptions,
                        indicatorColor = Color.Black,
                        containerColor = Color.Transparent,
//                    borderWidth = 1.dp,
                        dropDownColor = Color.Gray,
                        columnModifier = Modifier
                            .weight(0.5f)
                            .height((TEXT_INPUT_HEIGHT * 0.88).dp)
                            .padding(end = 29.dp)
                            .padding(bottom = 12.dp, top = 3.dp)
                            .align(Alignment.CenterVertically),
                        textFieldModifier = Modifier,
                        borderShape = RoundedCornerShape(4.dp),
                        onValueChange = { selection ->
                            govDataOption = selection to govDataText
                        }
                    )

                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = businessText,
                        onValueChange = {
                            businessText = it
                            businessOption = businessOption.first to it
                        },
                        label = { Text("Business Data (Optional)") },
                        modifier = Modifier
                            .weight(1f)
                            .height(TEXT_INPUT_HEIGHT.dp)
                            .padding(start = 32.dp)
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(Modifier.width(12.dp))
                    DojahDropDownInputField(
                        labelText = businessOption.first,
                        options = businessOptions,
                        indicatorColor = Color.Black,
                        containerColor = Color.Transparent,
//                    borderWidth = 1.dp,
                        dropDownColor = Color.Gray,
                        columnModifier = Modifier
                            .weight(0.5f)
                            .height((TEXT_INPUT_HEIGHT * 0.88).dp)
                            .padding(end = 29.dp)
                            .padding(bottom = 12.dp, top = 3.dp)
                            .align(Alignment.CenterVertically),
                        textFieldModifier = Modifier,
                        borderShape = RoundedCornerShape(4.dp),
                        onValueChange = { selection ->
                            businessOption = selection to businessText

                        }
                    )

                }
            }


            item {
                OutlinedTextField(
                    value = extraFirstNameText,
                    onValueChange = { extraFirstNameText = it },
                    label = { Text("FirstName (Optional)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TEXT_INPUT_HEIGHT.dp)
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = extraLastNameText,
                    onValueChange = { extraLastNameText = it },
                    label = { Text("LastName (Optional)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TEXT_INPUT_HEIGHT.dp)
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = extraDobText,
                    onValueChange = { extraDobText = it },
                    label = { Text("Date of Birth (Optional)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TEXT_INPUT_HEIGHT.dp)
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = extraEmailText,
                    onValueChange = { extraEmailText = it },
                    label = { Text("Email (Optional)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TEXT_INPUT_HEIGHT.dp)
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp)
                )
            }


            item {
                OutlinedTextField(
                    value = addressText,
                    onValueChange = { addressText = it },
                    label = { Text("Address (Optional)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TEXT_INPUT_HEIGHT.dp)
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp)
                )
            }
            item {
                OutlinedTextField(
                    value = latitudeText,
                    onValueChange = { latitudeText = it },
                    label = { Text("Latitude (Optional)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TEXT_INPUT_HEIGHT.dp)
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp)
                )
            }
            item {
                OutlinedTextField(
                    value = longitudeText,
                    onValueChange = { longitudeText = it },
                    label = { Text("Longitude (Optional)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TEXT_INPUT_HEIGHT.dp)
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp)
                )
            }

            item {
                Spacer(Modifier.height(100.dp))
            }
        }
        LaunchDojahButton() {
            if (widgetIdText.isNotBlank()) {
                DojahSdk.with(context).launch(
                    widgetIdText,
                    referenceId = refrenceIdText.ifBlank { null },
                    email = emailText.ifBlank { null },
                    dojahLauncher = dojahResultLauncher,
                    extraData = ExtraUserData(
                        userData = UserData(
                            firstName = extraFirstNameText,
                            lastName = extraLastNameText,
                            // format: dd-mm-yyy
                            dob = extraDobText,
                            email = extraEmailText,
                        ),
                        govId = GovId(
                            dl = govIdOption.second.takeIf { it.isNotBlank() && govIdOption.first == "dl" },
                            voter = govIdOption.second.takeIf { it.isNotBlank() && govIdOption.first == "voter" },
                            nin = govIdOption.second.takeIf { it.isNotBlank() && govIdOption.first == "nin" },
                            others = govIdOption.second.takeIf { it.isNotBlank() && govIdOption.first == "others" },
                            passport = govIdOption.second.takeIf { it.isNotBlank() && govIdOption.first == "passport" },
                            national = govIdOption.second.takeIf { it.isNotBlank() && govIdOption.first == "national" },
                        ),
                        govData = GovData(
                            dl = govDataOption.second.takeIf { it.isNotBlank() && govDataOption.first == "dl" },
                            nin = govDataOption.second.takeIf { it.isNotBlank() && govDataOption.first == "nin" },
                            bvn = govDataOption.second.takeIf { it.isNotBlank() && govDataOption.first == "bvn" },
                            vnin = govDataOption.second.takeIf { it.isNotBlank() && govDataOption.first == "vnin" },
                        ),
                        businessData = BusinessData(
                            cac = businessOption.second.takeIf { it.isNotBlank() && businessOption.first == "cac" },
                        ),
                        location = Location(
                            longitude = longitudeText.takeIf { it.isNotBlank() },
                            latitude = latitudeText.takeIf { it.isNotBlank() },
                        ),
                        address = addressText,
                        metadata = mapOf("key1" to "value1"),
                    )
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

@Composable
fun BoxScope.LaunchDojahButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 16.dp)
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