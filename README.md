# Cordova Leaderboard Plugin

## Overview
Cordova plugin for Google Play Games Leaderboard service for android.

You need to set up your game in Google Play Developer console.
Also have a look at https://developers.google.com/games/services/android/quickstart

## Installation

```bash
cordova plugin add cordova-plugin-leaderboard --variable APP_ID=YOUR_APP_ID
```

or

```bash
cordova plugin add https://github.com/princesanjivy/cordova-plugin-leaderboard.git --variable APP_ID=YOUR_APP_ID
```

## Usage

### Sign in

```javascript
leaderboard.init(
  function(){
    \\ on sign in success;
  },
  function(){
    \\ on failure ;
  }
);
```
