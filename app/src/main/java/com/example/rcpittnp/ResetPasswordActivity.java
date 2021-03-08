package com.example.rcpittnp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rcpittnp.MainActivity;
import com.example.rcpittnp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText enterEmailEditText;
    Button resetPwBtn;
    TextView returnToLoginScr;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        firebaseAuth = FirebaseAuth.getInstance();

        enterEmailEditText = (EditText) findViewById(R.id.fpResetEditText);
        resetPwBtn = (Button) findViewById(R.id.fpResetButton);
        returnToLoginScr = (TextView) findViewById(R.id.fpReturnToLoginTextView);

        resetPwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

        returnToLoginScr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResetPasswordActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void resetPassword(){

        String resetEmailStr = enterEmailEditText.getText().toString().trim();

        if (TextUtils.isEmpty(resetEmailStr)) {
            Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.sendPasswordResetEmail(resetEmailStr)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetPasswordActivity.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetPasswordActivity.this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}