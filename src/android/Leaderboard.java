// princesanjivy

package com.princewebstudio.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;


import android.content.Intent;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.OnSuccessListener;

public class Leaderboard extends CordovaPlugin{
  private static final int RC_SIGN_IN = 9001;
  private static final int RC_LEADERBOARD_UI = 9004;

  public CordovaPlugin reference = this;
  public CallbackContext callback;

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webview){
    super.initialize(cordova, webview);
  }

  @Override
  public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException{
    callback = callbackContext;

    if(action.equals("init")){
      GoogleSignInOptions signInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;
      GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this.cordova.getActivity(), signInOptions);
      Intent intent = googleSignInClient.getSignInIntent();

      cordova.setActivityResultCallback(this);
      cordova.startActivityForResult(this, intent, RC_SIGN_IN);

      return true;
    }
    else if(action.equals("setScore")){
      String leaderboard_id = data.getString(0);
      int score = Integer.valueOf(data.getString(1));

      setScore(leaderboard_id, score);

      return true;
    }
    else if(action.equals("showLeaderboard")){
      String leaderboard_id = data.getString(0);

      showLeaderboard(leaderboard_id);

      return true;
    }
    else{
      return false;
    }
  }

  private void setScore(String leaderboard_id, int score){
    Games.getLeaderboardsClient(this.cordova.getActivity(),
      GoogleSignIn.getLastSignedInAccount(this.cordova.getContext()))
      .submitScore(leaderboard_id, score);
  }

  private void showLeaderboard(String leaderboard_id){
    Games.getLeaderboardsClient(this.cordova.getActivity(),
      GoogleSignIn.getLastSignedInAccount(this.cordova.getContext()))
      .getLeaderboardIntent(leaderboard_id)
      .addOnSuccessListener(new OnSuccessListener<Intent>(){
        @Override
        public void onSuccess(Intent intent){
          cordova.setActivityResultCallback(reference);
          cordova.startActivityForResult(reference, intent, RC_LEADERBOARD_UI);
        }
      });
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent intent){
    super.onActivityResult(requestCode, resultCode, intent);

  }

}
