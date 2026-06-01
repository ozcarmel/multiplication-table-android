# Google Play Release Notes

## Current Status

The native Android app has been scaffolded in this repository.

Publishing to Google Play is not complete yet because it requires access to the owner's Google Play Console account, app signing decisions, screenshots, store listing content, privacy declarations, and final release review.

## Package

`com.ozcarmel.multiplicationtable`

## App Name

Hebrew: `לוח הכפל בקלות`

English: `Multiplication Made Easy`

## First Release Scope

- Native Kotlin app.
- Jetpack Compose UI.
- Hebrew default, English supported.
- Interactive 10x10 multiplication table.
- Explanation panel for each selected multiplication fact.
- Practice panel with ordered and mixed modes.
- Answer checking.
- Reveal answers.
- Clear practice state.
- No child-name feature.
- No personal data storage.

## Store Listing Draft

Full listing drafts are now stored in:

- `store-listing/he-IL.md`
- `store-listing/en-US.md`

### Short Description

Hebrew-first multiplication table practice for kids, with interactive learning and answer checking.

### Full Description

Multiplication Made Easy helps children practice and understand the multiplication table through a playful, interactive experience.

The app includes a 10x10 multiplication table, simple explanations, equal-group thinking, repeated addition, and a practice panel where children can answer exercises, check their work, reveal answers, and clear the board.

The app is designed for children from 3rd grade through 8th grade, with Hebrew as the default language and English support included.

### Key Features

- Interactive 10x10 multiplication table
- Hebrew-first RTL interface
- English language support
- Practice by multiplication table
- Ordered or mixed exercises
- Check answers instantly
- Reveal and clear buttons
- No account required
- No personal data collection

## Play Console Checklist

- Create app in Google Play Console.
- Choose default language and app name.
- Confirm app category: Education.
- Add app icon.
- Add feature graphic.
- Add phone screenshots.
- Fill store listing in Hebrew and English.
- Complete Data Safety form.
- Complete target audience and content rating.
- Configure app signing.
- Build signed Android App Bundle (`.aab`).
- Upload to internal testing first.
- Test install from Play internal testing.
- Promote to production after review.

## Prepared Files

- `privacy-policy.md`
- `store-listing/data-safety-answers.md`
- `store-listing/he-IL.md`
- `store-listing/en-US.md`

## Official References

- Create and set up an app: https://support.google.com/googleplay/android-developer/answer/9859152
- Play App Signing: https://support.google.com/googleplay/android-developer/answer/9842756
- Android app signing: https://developer.android.com/guide/publishing/app-signing
- Android App Bundles: https://developer.android.com/guide/app-bundle

## Build Goal

Create a signed release Android App Bundle:

```bash
./gradlew bundleRelease
```

On Windows:

```powershell
.\gradlew.bat bundleRelease
```

This requires Gradle wrapper or Android Studio/Gradle to be available locally.
