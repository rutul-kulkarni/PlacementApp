package com.example.rcpittnp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcpittnp.Adapter.PlacedCompaniesAdapter;
import com.example.rcpittnp.Model.StudentModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddPlacementStatusActivity extends AppCompatActivity {

    Button addCompanyBtn;
    EditText companyNameEt;
    DatabaseReference rootRef;
    DatabaseReference mRef;
    String userId;
    FirebaseAuth firebaseAuth;
    ArrayList<String> placedCompanies ;
    ProgressDialog loadingBar;
    RecyclerView companyNameRV;
    PlacedCompaniesAdapter companiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_placement_status);

        companyNameRV = findViewById(R.id.companyNameRV);
        addCompanyBtn = findViewById(R.id.addCompanyBtn);
        companyNameEt = findViewById(R.id.companyNameTV);
        firebaseAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();

        mRef = FirebaseDatabase.getInstance().getReference("users").child("student").child(userId);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StudentModel student = snapshot.getValue(StudentModel.class);
                placedCompanies = student.getPlacedCompanies();
                Log.d("Company", "onDataChange: "+placedCompanies);
                companiesAdapter = new PlacedCompaniesAdapter(placedCompanies , getBaseContext());
                LinearLayoutManager layoutManager =new LinearLayoutManager(getBaseContext() , LinearLayoutManager.VERTICAL,false);
                companyNameRV.setLayoutManager(layoutManager);
                companyNameRV.setAdapter(companiesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addCompanyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCompanyToDatabase();
            }
        });
    }

    void addCompanyToDatabase() {
        loadingBar.setTitle("Create Account");
        loadingBar.setMessage("Please wait, While we are checking credentials...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        String companyName = companyNameEt.getText().toString();
        userId = firebaseAuth.getCurrentUser().getUid();
        rootRef = FirebaseDatabase.getInstance().getReference("users").child("student").child(userId);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StudentModel studentModel = snapshot.getValue(StudentModel.class);
                placedCompanies = studentModel.getPlacedCompanies();
                if (placedCompanies != null && placedCompanies.size() > 0){
                    placedCompanies.add(companyName);
                    companiesAdapter.notifyDataSetChanged();
                }else
                {
                    placedCompanies = new ArrayList<>();
                    placedCompanies.add(companyName);
                }

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child("student").child(userId).child("placedCompanies");
                ref.setValue(placedCompanies).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        loadingBar.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(AddPlacementStatusActivity.this, "Updated..!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(AddPlacementStatusActivity.this, "Error Updating..!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}