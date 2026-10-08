# Android 4 - Device Matrix

| Configuration | Device (AVD) | Android version | API level | Screen resolution | Emulator / Physical | Scenario 1 | Scenario 2 | Scenario 3 |
|---|---|---|---|---|---|---|---|---|
| 1 | Pixel 5 | Android 13 | 33 | 1080 x 2340 | Emulator | Pass | Pass | Pass |
| 2 | Pixel 7 Pro | Android 14 | 34 | 1440 x 3120 | Emulator | Pass | Pass | Pass |
| 3 | Pixel 8 | Android 15 | 35 | 1080 x 2400 | Emulator | Pass | Pass | Pass |

- Scenario 1: send a message in johnWeek and verify it after reopening the chat.
- Scenario 2: send a question in the chat titled with my full name.
- Scenario 3: continue a conversation in the shared chat using another account.

## Notes

- The devices were run one after another. A parallel run on all three emulators at the same time was attempted, but it could not be completed because the computer could not handle three emulators together (app installation timed out and the test process failed to attach). No results from that attempt are recorded here.
- A physical device was not used.

## Screenshots

- `screenshots/android4/android13_pixel5.png`
- `screenshots/android4/android14_pixel7pro.png`
- `screenshots/android4/android15_pixel8.png`