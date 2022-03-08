package com.example.turismapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseUser userCurent;
    private Button loginButton, phoneButton;
    private EditText userEmail, userPassword;
    private TextView newAccountLink, forgetPassLink;

    private FirebaseAuth mAuth;
    private ProgressDialog lD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        mAuth.signOut();

        InitializareCampuri();
        newAccountLink.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SendToRegisterActivity();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowtoLogin();
            }
        });
    }

    private void InitializareCampuri() {
        loginButton=(Button)findViewById(R.id.button_login);
        userEmail=(EditText)findViewById(R.id.email_login);
        userPassword=(EditText)findViewById(R.id.password_login);
        newAccountLink=(TextView) findViewById(R.id.needNewAccount);
        lD=new ProgressDialog(this);
    }

    private void allowtoLogin() {
        String email=userEmail.getText().toString();
        String password=userPassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Enter an email, please!",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Enter a password, please!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            lD.setTitle("Sign In");
            lD.setMessage("Please wait");
            lD.setCanceledOnTouchOutside(true); //this loadingbar will not be disappeared until the new account has been created
            lD.show();
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                SendToMenuActivity();
                                Toast.makeText(LoginActivity.this,"Login successful!",Toast.LENGTH_SHORT).show();
                                lD.dismiss();
                            }
                            else
                            {
                                String mess=task.getException().toString();
                                Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();
                                lD.dismiss();
                            }
                        }
                    });
        }

    }

    private void SendToRegisterActivity() {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }

    //userul nu va fi trimis inapoi la activitatea de login odata ce a fost logat deja
    //pentru a fi trimis inapoi la login, trebuie sa apese pe Log out
    private void SendToMenuActivity() {
        Intent mIntent = new Intent(LoginActivity.this, MenuActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mIntent);
        finish();
    }
}