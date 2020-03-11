package com.example.pruebaandroid.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pruebaandroid.Models.User;
import com.example.pruebaandroid.R;
import com.example.pruebaandroid.Services.AuthService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;


public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegisterIntent;
    EditText edTxtEmail,edTxtPassword;
    AuthService authService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegisterIntent = findViewById(R.id.btnRegisterIntent);
        edTxtEmail = findViewById(R.id.edTxtEmail);
        edTxtPassword = findViewById(R.id.edTxtPassword);

        this.authService = AuthService.getAuthService();
        btnLogin.setOnClickListener(e->this.login());
        btnRegisterIntent.setOnClickListener(e->this.register());

        if(this.authService.isLogged){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }


    public void login(){
        if(this.edTxtEmail.getText().toString().isEmpty()&& this.edTxtPassword.getText().toString().isEmpty())return;
        User user = new User(this.edTxtEmail.getText().toString(),this.edTxtPassword.getText().toString());
        this.authService.login(user.email,user.password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User resultUser  = new User();
                    resultUser.email = LoginActivity.this.authService.mAuth.getCurrentUser().getEmail();
                    LoginActivity.this.authService.setUser(user);
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else{
                    Log.e("Login",task.getException().getMessage());
                    Toast.makeText(LoginActivity.this,"Error:"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    void register(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

}
