package com.example.joanericacanada.daybook.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.joanericacanada.daybook.R;

/**
 * Created by joanericacanada on 10/30/15.
 */
public class PasswordManager {
    //VARIABLES
    private static SharedPreferences passwordPrefs;
    private static PasswordManager passwordManager;
    private Context context;

    //TAGS
    private static final String KEY_WORD = "password";

    private PasswordManager(Context context){
        this.context = context;
        passwordPrefs = context.getSharedPreferences("PasswordFile", 0);
    }

    public String getPassword(){
        return passwordPrefs.getString(KEY_WORD, null);
    }

    public void setPassword(String password){
        passwordPrefs.edit().putString(KEY_WORD, password).commit();
    }

    public static PasswordManager get(Context c){
        if(passwordManager == null)
            passwordManager = new PasswordManager(c.getApplicationContext());
        return passwordManager;
    }

    public boolean validatePassword(String password1, String password2){
        if (!(isNull(password1) & isNull(password2))){
            if (password1.equals(password2))
                return true;
        }
        return false;
    }

    private boolean isNull(String password){
        if(password.equals("")) {
            Toast.makeText(context, R.string.input_null, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
