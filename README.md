# Dojah KYC SDK (Android)

## Requirements

| Requirement | Version |
|---|---|
| minSdk | 24 |
| compileSdk | 36 |
| targetSdk | 35 |
| JDK | 17 |
| Kotlin | 2.2.x |
| Core library desugaring | required |

The published AAR is compiled with **Kotlin 2.2.10** and **JVM 17**. Host apps on Kotlin 1.8/1.9/2.0 or Java 8/11 will fail to compile or resolve metadata.

## Installation [![](https://jitpack.io/v/dojah-inc/sdk-kotlin.svg)](https://jitpack.io/#dojah-inc/sdk-kotlin)

Add JitPack in `settings.gradle`:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
    }
}
```

Enable AndroidX and Jetifier in `gradle.properties`:

```properties
android.useAndroidX=true
android.enableJetifier=true
```

Host `app/build.gradle`:

```gradle
android {
    compileSdk 36

    defaultConfig {
        minSdk 24
        targetSdk 35
        missingDimensionStrategy 'deviceType', 'mobile'
    }

    compileOptions {
        coreLibraryDesugaringEnabled true
        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = '17'
    }
}

dependencies {
    coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs:2.1.5'
    implementation 'com.github.dojah-inc:sdk-kotlin:[latest-version]'
}
```

If the host app uses R8/minify, the SDK ships consumer ProGuard rules automatically. Do not strip `com.dojah.kyc_sdk_kotlin.**`.

If the host already uses OkHttp, pin **4.12.0** (or newer 4.x). Do not mix OkHttp 5 alphas.

If the host already uses Compose, use a single Compose BOM (this SDK uses `2026.06.01`).

## Usage

```kotlin
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.dojah.kyc_sdk_kotlin.DOJAH_RESULT_KEY
import com.dojah.kyc_sdk_kotlin.DojahSdk
import com.dojah.kyc_sdk_kotlin.domain.ExtraUserData

val dojahLauncher = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
) { result ->
    val status = result.data?.getStringExtra(DOJAH_RESULT_KEY)
}

DojahSdk.with(this).launch(
    dojahLauncher = dojahLauncher,
    widgetId = "{Required: Your_WidgetID}",
    referenceId = "{Optional: Reference_ID}",
    email = "{Optional: Email_Address}",
    extraData = ExtraUserData(), // optional
)
```

Older Activity APIs can use `launchWithBackwardCompatibility(activity, widgetId, ...)`.

### SDK Parameters

- `context` — required. Activity or application context used by `with(...)`.
- `dojahLauncher` — required for `launch(...)`. An `ActivityResultLauncher<Intent>`.
- `widgetId` — required. Created on the Dojah dashboard.
- `referenceId` — optional. Resume an ongoing verification.
- `email` — optional. Prefill or resume verification for a user.
- `extraData` — optional. Prefill user, government ID, and business fields.

Result extras use `DOJAH_RESULT_KEY` with `closed`, `approved`, `pending`, or `failed`.

## How to Get a Widget ID

To use the SDK, you need a WidgetID, which is a required parameter for initializing the SDK. You can obtain this by creating a flow on the Dojah platform. Follow these steps to configure and get your Widget ID:

```txt
1. Log in to your Dojah Dashboard: If you don’t have an account, sign up on the Dojah platform.

2. Navigate to the EasyOnboard Feature: Once logged in, find the EasyOnboard section on your dashboard.

3. Create a Flow:

    - Click on the 'Create a Flow' button.
    - Name Your Flow: Choose a meaningful name for your flow, which will help you identify it later.

4. Add an Application:

    - Either create a new application or add an existing one.
    - Customise your widget with your brand logo and color by selecting an application.

5. Configure the Flow:

    - Select a Country: Choose the country or countries relevant to your verification process.
    - Select a Preview Process: Decide between automatic or manual verification.
    - Notification Type: Choose how you’d like to receive webhook notifications, send verification status to your user’s email or add a support email for your customers to easily reach out to you directly if they encounter any blocker during verification.
    - Add Verification Pages: Customize the verification steps in your flow (e.g., ID verification, address verification, etc.).

6. Publish Your Widget: After configuring your flow, publish the widget. Once published, your flow is live.

7. Copy Your Widget ID: After publishing, the platform will generate a Widget ID. Copy this Widget ID as you will need it to initialize the SDK as stated above.
```
