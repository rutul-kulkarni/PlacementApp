package com.example.rcpittnp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rcpittnp.Model.StudentModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText firstNameEt , lastNameEt , emailEt ,passwordEt , mobileNumberEt , prnEt , sscMarksEt , hscMarksEt , diplomaMarksEt , currentCgpaEt ,activeBacklogEt , yearGapEt;
    TextView returnToLoginTextV;
    Button signUpBtn;
    ProgressDialog loadingBar;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference rootRef;
    String userId;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        firstNameEt = findViewById(R.id.firstNameEt);
        lastNameEt = findViewById(R.id.lastNameEt);
        emailEt = findViewById(R.id.emailIdEt);
        passwordEt = findViewById(R.id.passwordEt);
        prnEt = findViewById(R.id.prnEt);
        mobileNumberEt = findViewById(R.id.phoneNumberEt);
        sscMarksEt = findViewById(R.id.sscMarksEt);
        hscMarksEt = findViewById(R.id.hscMarksEt);
        diplomaMarksEt = findViewById(R.id.diplomaMarksEt);
        activeBacklogEt = findViewById(R.id.activeBacklog);
        yearGapEt = findViewById(R.id.yearGap);
        currentCgpaEt = findViewById(R.id.currentCgpaEt);
        returnToLoginTextV = (TextView) findViewById(R.id.loginReturnTextView);

        signUpBtn = (Button) findViewById(R.id.signUpButton);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
        returnToLoginTextV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        createAuthStateListener();
    }

    public void createAccount()
    {
        String firstName = firstNameEt.getText().toString().trim();
        String lastName = lastNameEt.getText().toString().trim();
        String email = emailEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        String mobileNumber = mobileNumberEt.getText().toString().trim();
        String sscMarks = sscMarksEt.getText().toString().trim();
        String hscMarks = hscMarksEt.getText().toString().trim();
        String diplomaMarks = diplomaMarksEt.getText().toString().trim();
        String currentCgpa = currentCgpaEt.getText().toString().trim();
        String yearGap = yearGapEt.getText().toString().trim();
        String activeBacklog = activeBacklogEt.getText().toString().trim();
        String prn = prnEt.getText().toString().trim();

        if(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email) ||TextUtils.isEmpty(password)||TextUtils.isEmpty(mobileNumber)
                || TextUtils.isEmpty(sscMarks) || TextUtils.isEmpty(currentCgpa) || TextUtils.isEmpty(yearGap) || TextUtils.isEmpty(activeBacklog) || TextUtils.isEmpty(prn))
        {
            Toast.makeText(this, "All fields are necessary", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, While we are checking credentials...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            firebaseAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if ((task.isSuccessful()))
                    {
                        boolean hasdiploma = false , hasyearGap = false , hasactiveBacklog = false;
                        if(diplomaMarks != null)
                            hasdiploma = true;
                        if(!yearGap.equals("0"))
                            hasyearGap = true;
                        if(!activeBacklog.equals("0"))
                            hasactiveBacklog = true;

                        StudentModel student = new StudentModel(firstName ,lastName , email ,mobileNumber ,prn ,sscMarks ,hscMarks , hasdiploma ,diplomaMarks , hasyearGap ,hasactiveBacklog ,currentCgpa);
                        rootRef = FirebaseDatabase.getInstance().getReference();
                        userId = firebaseAuth.getCurrentUser().getUid();
                        rootRef.child("users").child("student").child(userId).setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(SignUpActivity.this, "Student data added!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(SignUpActivity.this, "Error Adding data", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpActivity.this , MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });

                    }
                    else
                    {
                        Toast.makeText(SignUpActivity.this, "Error creating account...", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }

                }
            });
        }

    }

    private void createAuthStateListener() {
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

        };
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }
}