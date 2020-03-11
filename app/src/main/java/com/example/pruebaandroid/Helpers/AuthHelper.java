package com.example.pruebaandroid.Helpers;

import android.content.SharedPreferences;

public class AuthHelper {
    public static String getEmail(SharedPreferences preferences){
        return preferences.getString("email", "");
    }

    public static String getPassword(SharedPreferences preferences){
        return preferences.getString("password", "");
    }
}
