package com.example.rcpittnp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.rcpittnp.Admin.AddCampusNotice;
import com.example.rcpittnp.Model.StudentModel;
import com.example.rcpittnp.ResumeModule.ResumeBuilderActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        nav=(NavigationView)findViewById(R.id.navmenu);
        drawerLayout =(DrawerLayout)findViewById(R.id.drawer);

        toggle=new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        ArrayList<StudentModel> list = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference();
        String uid = firebaseAuth.getCurrentUser().getUid();
        reference = reference.child("users").child("student").child(uid);

        /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    StudentModel student = dataSnapshot.getValue(StudentModel.class);
                    if(student.getId().equalsIgnoreCase(firebaseAuth.getUid()))
                        list.add(student);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        Log.d("TAG", "onCreate: "+list.size());

        View view = nav.getHeaderView(0);
        TextView usernameTv = view.findViewById(R.id.userName);
        TextView prnTv = view.findViewById(R.id.prn);
        String nameStr = "Username";
        String prnStr = firebaseAuth.getCurrentUser().getEmail();
        if(list.size()>0 && list.get(0) != null)
        {
            nameStr = list.get(0).getFirstName()+" "+list.get(0).getLastName();
            prnStr =  list.get(0).getPrnNumber();
        }
        usernameTv.setText(nameStr);
        prnTv.setText(prnStr);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.viewnotice :
                        Intent intent3 = new Intent(ProfileActivity.this , ViewNotice.class);
                        startActivity(intent3);
                        Toast.makeText(getApplicationContext(),"View Notice is open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.ApplayCampus :
                        Toast.makeText(getApplicationContext(),"Campus Driver is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.adddriverque :
                        Toast.makeText(getApplicationContext(),"Add Question for Drive",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.givequiz :
                        Intent intent = new Intent(ProfileActivity.this , QuizActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Give Quiz is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.resumebuil :
                        Intent intent1 = new Intent(ProfileActivity.this , ResumeBuilderActivity.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(),"Resume builder is open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.addCampusNotice:
                        Intent intent2 = new Intent(ProfileActivity.this , AddCampusNotice.class);
                        startActivity(intent2);
                        Toast.makeText(getApplicationContext(),"Add Campus Notice is open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logoutmenu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout)
        {
            firebaseAuth.signOut();
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Toast.makeText(this, "Logged Out....", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}