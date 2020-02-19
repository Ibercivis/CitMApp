package com.ibercivis.mapp.clases;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

public class SessionManager {

    /* http://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/ */

    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "CitMAppLogin";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_ID = "userID";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_NAME = "username";
    private static final HashMap<String,String> mapaPass = new HashMap<String, String>();

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setClear(){
        editor.putBoolean(KEY_IS_LOGGEDIN, false);
        editor.putString(KEY_NAME, "");
        editor.putString(KEY_ID, "");
        editor.putString(KEY_TOKEN, "");

        editor.commit();

        Log.d(TAG, "User session closed!");

    }

    public void setLogin(boolean isLoggedIn, String username) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString(KEY_NAME, username);

        // commit changes
        editor.commit();

        Log.d(TAG, "User activity_login session modified!");
    }

    public void setKeys(String token, int id){

        editor.putString(KEY_TOKEN, token);
        editor.putInt(KEY_ID, id);

        editor.commit();

        Log.d(TAG, "User keys modified!");
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getUsername() {
        return pref.getString(KEY_NAME, "");
    }

    public String getToken(){
        return pref.getString(KEY_TOKEN, "");
    }

    public int getIdUser(){
        return pref.getInt(KEY_ID, 0);
    }

    public void putPass(String titulo, String pass){

        mapaPass.put(titulo, pass);

    }

    public String getPass(String titulo){

        String pass_proyecto = mapaPass.get(titulo);

        return  pass_proyecto;
    }

}
