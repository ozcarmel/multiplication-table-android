# Multiplication Table Android App Plan

## Project Goal

Build a separate Android app for the multiplication table learning experience, without mixing it with the existing web app.

The current web app remains in:

`ozcarmel/multiplication-table`

The Android app will live in:

`ozcarmel/multiplication-table-android`

## Repository Decision

Use a separate GitHub repository for Android.

Reasoning:

- The web app is already stable and should stay focused.
- Android has a different project structure: Gradle, Android Studio, Kotlin, app signing, emulator setup, Android resources, and native UI code.
- A separate repository keeps commits, releases, issues, and future publishing steps cleaner.
- It reduces the chance of web and Android files interfering with each other.

## Recommended Technology

Use native Android with Kotlin and Jetpack Compose.

This is the preferred path because the app is meant for children and should feel polished, responsive, and native on Android devices.

Alternative considered:

Use a WebView wrapper around the existing web app.

Why not start there:

- It is faster at first, but less native.
- It can feel like a website inside an app.
- Offline behavior, gestures, accessibility, and Play Store polish are usually better with native Compose.

## Product Direction

The Android app should keep the same educational spirit as the web app:

- Hebrew first, English supported.
- Default direction in Hebrew is RTL.
- Suitable for 3rd grade through 8th grade.
- Friendly, playful, and not visually overwhelming.
- Clear multiplication explanations.
- Interactive 10x10 multiplication table.
- Practice panel where children choose a multiplication table and answer exercises.
- Ordered and mixed practice modes.
- Check answers with clear feedback.
- Reveal answers when needed.
- Clear/reset practice state.

## Important UX Decisions From The Web App

- Do not include the child name feature for now.
- Do not save personal data in local storage or Android storage.
- Keep the landing screen focused on the multiplication table and practice.
- Avoid text that appears reversed or badly aligned in Hebrew.
- Hebrew screens must be RTL and right-aligned where appropriate.
- Math expressions should remain readable left-to-right.
- Repeated addition should be shown on a separate line so it does not wrap awkwardly.

## Suggested Android Screens

### Main Screen

Contains:

- Header: `לוח הכפל בקלות`
- Language switch: Hebrew / English
- Interactive 10x10 multiplication table
- Explanation panel for the selected multiplication fact
- Practice panel

### Practice Panel

Contains:

- Choose multiplication table: 1 through 10
- Mode selector: ordered / mixed
- Reveal button
- Clear button
- Exercise rows with:
  - multiplication exercise
  - numeric answer input
  - check icon
  - correct / incorrect feedback

## Android Implementation Notes

Use:

- Kotlin
- Jetpack Compose
- Material 3
- Gradle Kotlin DSL if possible

Suggested package name:

`com.ozcarmel.multiplicationtable`

Suggested architecture:

- `MainActivity`
- `ui/`
- `ui/components/`
- `ui/theme/`
- `model/`

Initial implementation can keep state locally in Compose with `rememberSaveable`.

No backend is needed.

## First Development Milestone

Create a minimal native Android app with:

- Hebrew default UI
- Language toggle
- 10x10 multiplication table
- Selected fact explanation
- Practice panel with answer checking

## Later Improvements

Possible future features:

- Badges and progress
- Timed practice mode
- Printable or shareable chart
- Sound effects with mute option
- Parent/teacher settings
- Accessibility tuning for larger text
- Offline-first release-ready app

