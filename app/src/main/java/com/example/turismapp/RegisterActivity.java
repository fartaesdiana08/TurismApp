package com.example.turismapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private Button registerButton;
    private EditText userEmail_register, userPassword_register;
    private TextView oldAccount;
    private FirebaseAuth mAuth;

    private ProgressDialog loadingBar;

    private DatabaseReference radRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        InitializeFields();
        radRef= FirebaseDatabase.getInstance().getReference();

        oldAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendToLoginActivity();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                creareContNou();
            }
        });
    }

    private void creareContNou() {
        String email=userEmail_register.getText().toString();
        String password=userPassword_register.getText().toString();

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
            loadingBar.setTitle("Creating new account");
            loadingBar.setMessage("Please wait");
            loadingBar.setCanceledOnTouchOutside(true); //this loadingbar will not be disappeared until the new account has been created
            loadingBar.show();
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                String idUserCurent=mAuth.getCurrentUser().getUid();
                                radRef.child("User").child(idUserCurent).setValue("");

                                SendToMenuActivity();
                                Toast.makeText(RegisterActivity.this,"Account created succesfully",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                            }
                            else
                            {
                                String mess=task.getException().toString();
                                Toast.makeText(RegisterActivity.this,"Error"+mess,Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    private void SendToMenuActivity() {
        Intent mIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mIntent);
        finish();
    }

    private void InitializeFields() {
        registerButton=(Button)findViewById(R.id.button_register);
        userEmail_register=(EditText)findViewById(R.id.email_register);
        userPassword_register=(EditText)findViewById(R.id.password_register);
        oldAccount=(TextView) findViewById(R.id.have_anAccount2);
        loadingBar=new ProgressDialog(this);
    }
    private void SendToLoginActivity() {
        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}