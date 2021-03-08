package com.example.rcpittnp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText emailEditText;
    EditText passwordEditText;
    Button loginBtn;
    TextView resetBtn;
    TextView createAccountBtn;
    ProgressDialog loadingBar;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginBtn = (Button) findViewById(R.id.loginbutton);
        resetBtn = (TextView) findViewById(R.id.resetPwTextView);
        createAccountBtn = (TextView) findViewById(R.id.createAccTextView);
        firebaseAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        if(firebaseAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    public void loginUser(){

        String email = emailEditText.getText().toString().trim();
        String password  = passwordEditText.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            loadingBar.setTitle("Logging in...!");
            loadingBar.setMessage("Please wait, we are checking credentials...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                loadingBar.dismiss();
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this, "Logged in....!", Toast.LENGTH_SHORT).show();
                                finish();
                                // updateUI(user);
                            } else {
                                loadingBar.dismiss();
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                // updateUI(null);
                            }


                        }
                    });
        }



    }



    @Override
    public void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }


}