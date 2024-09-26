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

Import dependency in your app/build.gradle

```
...
dependencies {
  ...
  // Dojah Kyc SDK
  implementation 'com.dojah_inc:dojah_android_sdk:[version]'
}
```


## Usage

```kotlin
import com.dojah_inc.dojah_android_sdk.DojahSdk
 
DojahSdk.with(context).launch(
    "{Required: Your_WidgetID}",
    referenceId = "{Optional: Reference_ID}",
    email = “{Optional: Email_Address}”,
)

```

### SDK Parameters
- `WidgetID` - a `REQUIRED` parameter. You get this ID when you sign up on the Dojoh platform
- `Reference ID` - an `OPTIONAL` parameter that allows you to initialize the SDK for an ongoing verification.
- `Email Address` - an `OPTIONAL` parameter that allows you to initialize the SDK for an ongoing verification


`REMEMBER TO CHANGE THE WIDGET ID,referenceId, AND email BEFORE RUNING YOUR APP`
