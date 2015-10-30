package com.example.joanericacanada.daybook.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by joanericacanada on 10/30/15.
 */
public class PasswordManager {
    //VARIABLES
    private static SharedPreferences passwordPrefs;
    private static PasswordManager passwordManager;

    //TAGS
    private static final String KEY_WORD = "password";

    public PasswordManager(Context context){
        passwordPrefs = context.getSharedPreferences("PasswordFile", 0);
    }

    public String getPassword(){
        return passwordPrefs.getString(KEY_WORD, null);
    }

    public void setPassword(String password){
        passwordPrefs.edit().putString(KEY_WORD, password);
    }

    public static PasswordManager get(Context c){
        if(passwordManager == null) {
            passwordManager = new PasswordManager(c.getApplicationContext());
            Log.e("passwordManager", "null");
        }
        return passwordManager;
    }
}
