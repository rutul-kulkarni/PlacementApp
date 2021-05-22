package com.example.rcpittnp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rcpittnp.Model.HrContact;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddHrContactActivity extends AppCompatActivity {

    Button clearBtn, addBtn;
    DatabaseReference rootRef;
    String userId;
    ProgressDialog loadingBar;
    FirebaseAuth firebaseAuth;
    EditText hrNameEt, companyNameEt, mobNumberEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hr_contact);

        hrNameEt = findViewById(R.id.hrName);
        companyNameEt = findViewById(R.id.companyName);
        mobNumberEt = findViewById(R.id.mobNumber);
        clearBtn = findViewById(R.id.btnClear);
        addBtn = findViewById(R.id.btnAdd);
        firebaseAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addHrContacttoDatabase();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hrNameEt.setText("");
                companyNameEt.setText("");
                mobNumberEt.setText("");
            }
        });
    }

    void addHrContacttoDatabase() {
        rootRef = FirebaseDatabase.getInstance().getReference("hrcontact").push();
        String hrName = hrNameEt.getText().toString();
        String companyName = companyNameEt.getText().toString();
        String mobileNum = mobNumberEt.getText().toString();

        if (hrName == null || companyName == null || mobileNum == null) {
            Toast.makeText(this, "All Fields Are Necesary..!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            HrContact contact = new HrContact(hrName , companyName , mobileNum);
            rootRef.setValue(contact).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                        Toast.makeText(AddHrContactActivity.this, "Contact Added..!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(AddHrContactActivity.this, "Error...!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}