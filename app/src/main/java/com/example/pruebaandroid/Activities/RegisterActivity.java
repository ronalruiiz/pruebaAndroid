package com.example.pruebaandroid.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pruebaandroid.R;
import com.example.pruebaandroid.Services.AuthService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;
    EditText edTxtPasswordRegister,edTxtEmailRegister;
    AuthService authService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnRegister);
        authService  = AuthService.getAuthService();
        edTxtPasswordRegister = findViewById(R.id.edTxtPasswordRegister);
        edTxtEmailRegister = findViewById(R.id.edTxtEmailRegister);

        btnRegister.setOnClickListener(e->this.register());
    }

    void register(){
        String email = this.edTxtEmailRegister.getText().toString(), password = this.edTxtPasswordRegister.getText().toString();
        if(!email.isEmpty() && !password.isEmpty()){
            this.authService.register(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(RegisterActivity.this,"Error:"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}
