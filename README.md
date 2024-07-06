
# Loan lender application with credo-sdk

## Standard flow for Mobile
- Initialize credoapp SDK
- Collect credolab DataSet
- Check reference number uniqueness
- Collect DataSet
- Collect Iovation DataSet**
- Upload credolab DataSet
- Login
- Get credolab Score
- Get Iovation fraudulent analysis report**
- Get Iovation analysis report**


## Getting started

`$ yarn add file:../credoappsdk`

#### iOS

`$ cd ios && pod install && cd ..`
react-native run-ios
#### Android

react-native run-android for bareworkflow

## Usage
```javascript
 import { NativeModules } from 'react-native';

await NativeModules.RNCredoappsdk.execute
```

# Android
#### Behavioral plugin designed to capture metadata related to the user’s in-app behavior, such as touch, taps, text input, scroll, etc.

# iOS
#### Behavioral plugin designed to capture metadata related to the user’s in-app behavior, such as touch, taps, text input, scroll, etc.

# Modules
### Android:
- Behavioral 4.3.1

### iOS:
- Behavioral 3.4.0


# Android
#### Core Proxy Encrypt plugin designed to capture a client’s digital footprint from an Android device and upload it to credolab web service for future processing of scorecards and fragments.

# iOS
#### Core Proxy Encrypt plugin designed to capture a client’s digital footprint from an iOS device and upload it to the credolab web service for future processing of scorecards and fragments.
# Modules
### Android:
- Core 4.6.0

### iOS:
- Core 3.4.0
