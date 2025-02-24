# Dojah KYC SDK (Android)

## Requirements
* Minimum Android SDK version - 21
* Supported targetSdkVersion - 34

## Installation

Set maven path in your root/build.gradle file:
```
...
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
        maven {
            url = uri("https://maven.pkg.github.com/dojah-inc/sdk-kotlin")
            credentials {
                username = "dojah-inc"
                password = "[TO BE ADDED SOON]"
            }
        }
    }
}
```
Or Set maven path in your root/settings.gradle file:
```
...
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
        maven {
            url = uri("https://maven.pkg.github.com/dojah-inc/sdk-kotlin")
            credentials {
                username = "dojah-inc"
                password = "[TO BE ADDED SOON]"
            }
        }
    }
}
```
Enable jetifier in grade.properties:
```
android.enableJetifier=true
```

Add dojah dependency in your app/build.gradle

```
...
dependencies {
  ...
  // Dojah Kyc SDK
  implementation 'com.dojah_inc:dojah_android_sdk:[latest-version]'
}
```


## Usage
To start KYC, import Dojah in your code, and launch Dojah Screen

```kotlin
import com.dojah_inc.dojah_android_sdk.DojahSdk
 
DojahSdk.with(context).launch(
    "{Required: Your_WidgetID}",
    referenceId = "{Optional: Reference_ID}",
    email = “{Optional: Email_Address}”,
)

```

### SDK Parameters
- `context` - `REQUIRED` parameter. Your Activity Context.
- `WidgetID` - a `REQUIRED` parameter. You get this ID when you sign up on the Dojah platform, follow the next step to generate your WidgetId.
- `Reference ID` - an `OPTIONAL` parameter that allows you to initialize the SDK for an ongoing verification.
- `Email Address` - an `OPTIONAL` parameter that allows you to initialize the SDK for an ongoing verification.

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
