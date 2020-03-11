package com.example.pruebaandroid.Services;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.pruebaandroid.Helpers.AuthHelper;
import com.example.pruebaandroid.Models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

public class AuthService {
    private User user;
    private SharedPreferences preferences;
    public Boolean isLogged = false;
    public FirebaseAuth mAuth;
    private static AuthService authServices;

    private AuthService(SharedPreferences preferences) {
        this.preferences = preferences;
        mAuth = FirebaseAuth.getInstance();
        getSharedPreferences();
    }

    public static AuthService getAuthService(SharedPreferences preferences) {
        if (AuthService.authServices == null && preferences != null) {
            AuthService.authServices = new AuthService(preferences);
            return AuthService.authServices;
        } else {
            return AuthService.authServices;
        }
    }

    public static AuthService getAuthService() {
        return AuthService.getAuthService(AuthService.authServices.preferences);
    }

    private void getSharedPreferences() {
        if(this.preferences.contains("user") && this.preferences.contains("logged")){
            this.user = new Gson().fromJson(this.preferences.getString("user",""),User.class);
            this.isLogged = this.preferences.getBoolean("logged",false);
        }
    }

    public User getUser() {
        return user;
    }

    public boolean isLogin() {
        return this.preferences.getBoolean("logged", false);
    }

    public boolean isRemember() {
        return this.preferences.getBoolean("remember", false);
    }


    public Task<AuthResult> login(String email, String password) {
        return this.mAuth.signInWithEmailAndPassword(email,password);
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() > 4;
    }

    public void setUser(User user) {
        if (user != null) {
            this.isLogged = true;
            this.user = user;
            this.preferences.edit().putString("user", new Gson().toJson(user))
                    .putBoolean("logged",this.isLogged).apply();
        }
    }
    public boolean logout() {
        try {
            FirebaseAuth.getInstance().signOut();
            this.resetAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean resetAll(){
        this.isLogged = false;
        this.user = null;
        this.preferences.edit().clear().apply();
        return true;
    }

    public Task<AuthResult> register(String email, String password){
        return mAuth.createUserWithEmailAndPassword(email, password);
    }


}
