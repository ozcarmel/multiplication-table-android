# Google Play Data Safety Draft Answers

Use this as a draft when filling the Data Safety form in Play Console.

## Data Collection

Does the app collect or share any required user data types?

Suggested answer: No.

Reason:

- The app does not require login.
- The app does not collect names.
- The app does not collect email addresses.
- The app does not collect location.
- The app does not collect photos, contacts, files, messages, health data, financial data, or device identifiers.
- The app does not include analytics SDKs.
- The app does not include ads SDKs.
- The app does not send user input to a server.

## Data Sharing

Does the app share user data with third parties?

Suggested answer: No.

## Data Encryption

Is data encrypted in transit?

Suggested answer: Not applicable if no user data is collected or transmitted.

## Data Deletion

Can users request data deletion?

Suggested answer: Not applicable if no user data is collected.

## Privacy Policy

Even if no data is collected, Google Play requires completing the Data Safety form and may require a publicly accessible privacy policy, especially because the app is made for children.

Recommended privacy policy file:

`privacy-policy.md`

## Families / Children

The app is educational and intended for children.

Suggested declaration:

- Category: Education
- Ads: No ads
- In-app purchases: No
- User-generated content: No
- Online communication between users: No
- Login/account creation: No
- Personal data collection: No

## Official References

- Data Safety form: https://support.google.com/googleplay/android-developer/answer/10787469
- Families program: https://play.google.com/console/about/programs/families/
- Target audience and app content: https://support.google.com/googleplay/android-developer/answer/9867159
