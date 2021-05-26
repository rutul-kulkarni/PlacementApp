package com.example.rcpittnp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcpittnp.Adapter.ViewHRContactAdapter;
import com.example.rcpittnp.Model.HrContact;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewHRContact extends AppCompatActivity {

    RecyclerView hrContactRv;
    DatabaseReference rootRef;
    FirebaseAuth firebaseAuth;
    String userId;
    ProgressDialog loadingBar;
    List<HrContact> hrContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_h_r_contact);
        hrContacts = new ArrayList<>();
        hrContactRv = findViewById(R.id.viewHrContactRv);
        rootRef = FirebaseDatabase.getInstance().getReference("hrcontact");
        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Adding Placement Status");
        loadingBar.setMessage("Please wait...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        getDataFromDatabase();
    }

    public void getDataFromDatabase(){
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keySnapshot : snapshot.getChildren())
                {
                    HrContact hrContact = keySnapshot.getValue(HrContact.class);
                    Log.d("Company", "onDataChange: "+hrContact.getHrName());
                    hrContacts.add(hrContact);
                }
                ViewHRContactAdapter adapter = new ViewHRContactAdapter(getBaseContext() , hrContacts);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext() , LinearLayoutManager.VERTICAL,false);
                hrContactRv.setLayoutManager(layoutManager);
                hrContactRv.setAdapter(adapter);
                loadingBar.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}