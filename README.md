# Multiplication Table Android

Native Android version of the multiplication table learning app.

This repository is separate from the web app repository so Android project files, Gradle, signing, and Play Store release work do not mix with the web app.

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Hebrew-first RTL UI
- English language toggle

## Current Features

- Interactive 10x10 multiplication table
- Selected multiplication explanation
- Equal-groups explanation
- Repeated addition on its own line
- Practice panel
- Practice table selector from 1 to 10
- Ordered and mixed practice modes
- Numeric answer input
- Check answer button
- Reveal answers
- Clear practice state
- No personal data collection

## Local Setup

Open this folder in Android Studio:

`C:\Users\OZ\Documents\multiplication-table-android`

If Android Studio asks to sync Gradle, accept.

The local machine used to scaffold this repo did not have Android Studio, Android SDK tools, or Gradle on PATH, so the app was scaffolded but not compiled locally yet.

## Build

Once Android Studio or Gradle is available:

```powershell
.\gradlew.bat assembleDebug
```

For Google Play release, build an Android App Bundle:

```powershell
.\gradlew.bat bundleRelease
```

If the Gradle wrapper is not present yet, open the project in Android Studio and let it generate/sync the wrapper, or run:

```powershell
gradle wrapper
```

from a machine that has Gradle installed.

## Google Play

The app cannot be uploaded to Google Play from here without:

- Google Play Console access
- App signing setup
- A signed Android App Bundle
- Store listing content
- Screenshots
- Data Safety form
- Target audience and content rating forms

See [PLAY_STORE_RELEASE.md](PLAY_STORE_RELEASE.md) for the release checklist and listing draft.

