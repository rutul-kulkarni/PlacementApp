package com.example.rcpittnp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcpittnp.Adapter.ViewNoticeRVAdapter;
import com.example.rcpittnp.Model.Notice;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewNotice extends AppCompatActivity {

    RecyclerView viewNoticeRV;
    DatabaseReference rootRef;
    List<Notice> notices;
    FirebaseAuth firebaseAuth;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notice);

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        viewNoticeRV = findViewById(R.id.viewnoticeRV);
        notices = new ArrayList<>();
        rootRef = FirebaseDatabase.getInstance().getReference("notices");
        getDataFromDatabase();
    }
    public void getDataFromDatabase()
    {
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keySnapshot : snapshot.getChildren())
                {
                    Notice notice = keySnapshot.getValue(Notice.class);
                    Log.d("Company", "onDataChange: "+notice.getCompanyName());
                    notices.add(notice);
                }
                ViewNoticeRVAdapter adapter = new ViewNoticeRVAdapter(notices , getBaseContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext() , LinearLayoutManager.VERTICAL,false);
                viewNoticeRV.setLayoutManager(layoutManager);
                viewNoticeRV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}