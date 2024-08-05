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
enable jetifier in grade.properties:
```
android.enableJetifier=true
```

and add tools:replace="android:theme" at application level in your AndroidManifest.xml
```
tools:replace="android:theme"
```

import dependency in your app/build.gradle

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
    “[ENTER WIDGET_ID]”,
    referenceId = “[OPTIONAL]”,
    email = “[OPTIONAL]”,
)

```


## Deployment 

`REMEMBER TO CHANGE THE WIDGET ID,referenceId, AND email BEFORE RUNING YOUR APP`


## Additional information

Contact Dojah for more options for the config object.
