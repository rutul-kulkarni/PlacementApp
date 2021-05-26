package com.example.rcpittnp;

import android.app.ProgressDialog;
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
import com.example.rcpittnp.NewQuizModule.StudentQuizMainActivity;
import com.example.rcpittnp.Admin.QuizAdminModule.AdminCategoryActivity;
import com.example.rcpittnp.ResumeModule.ResumeBuilderActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        loadingBar.setTitle("Loading Data");
        loadingBar.setMessage("Please wait...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        nav = (NavigationView) findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        ArrayList<StudentModel> list = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference();
        String uid = firebaseAuth.getCurrentUser().getUid();
        reference = reference.child("users").child("student").child(uid);
        Log.d("TAG", "onCreate: " + list.size());

        View view = nav.getHeaderView(0);
        TextView usernameTv = view.findViewById(R.id.userName);
        TextView prnTv = view.findViewById(R.id.prn);
        String nameStr = "Username";
        String prnStr = firebaseAuth.getCurrentUser().getEmail();
        if (list.size() > 0 && list.get(0) != null) {
            nameStr = list.get(0).getFirstName() + " " + list.get(0).getLastName();
            prnStr = list.get(0).getPrnNumber();
        }
        usernameTv.setText(nameStr);
        prnTv.setText(prnStr);

        TextView studentNameTv = findViewById(R.id.fullNameTv);
        TextView prn = findViewById(R.id.prnTv);
        TextView mobNoTv = findViewById(R.id.mobNoTv);
        TextView sscTv = findViewById(R.id.hscTv);
        TextView hscTv = findViewById(R.id.sscTv);
        TextView placedCompaniesTv = findViewById(R.id.placedCompaniesTv);
        TextView cgpaTv = findViewById(R.id.cgpa);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StudentModel student = snapshot.getValue(StudentModel.class);
                String username = capitalizeName(student.getFirstName()) + " " + capitalizeName(student.getLastName());
                String prnStr = student.getPrnNumber();
                usernameTv.setText(username);
                prnTv.setText(prnStr);
                studentNameTv.setText("Full Name :  " + username);
                prn.setText("PRN NO. :  " + prnStr);
                mobNoTv.setText("Mobile No. :  " + student.getMobNumber());
                sscTv.setText("SSC MARKS :  " + student.getSscMarks());
                hscTv.setText("HSC MARKS :  " + student.getHscMarks());
                cgpaTv.setText("CGPA :  " + student.getCgpa());
                String placedComp = "";
                if (student.getPlacedCompanies() != null)
                    for (int i = 0; i < student.getPlacedCompanies().size(); i++) {
                        placedComp = placedComp + " " + (i + 1) + ". " + student.getPlacedCompanies().get(i);
                    }
                placedCompaniesTv.setText("PLACED COMPANIES :  " + placedComp);
                loadingBar.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.viewnotice:
                        Intent intent3 = new Intent(ProfileActivity.this, ViewNotice.class);
                        startActivity(intent3);
                        Toast.makeText(getApplicationContext(), "View Notice is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.resumebuil:
                        Intent intent1 = new Intent(ProfileActivity.this, ResumeBuilderActivity.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "Resume builder is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.addCampusNotice:
                        Intent intent2 = new Intent(ProfileActivity.this, AddCampusNotice.class);
                        startActivity(intent2);
                        Toast.makeText(getApplicationContext(), "Add Campus Notice is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.addPlacementStatus:
                        Intent intent4 = new Intent(ProfileActivity.this, AddPlacementStatusActivity.class);
                        startActivity(intent4);
                        Toast.makeText(getApplicationContext(), "Add Placement Status is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.addFeedback:
                        Intent intent5 = new Intent(ProfileActivity.this, AddFeedbackActivity.class);
                        startActivity(intent5);
                        Toast.makeText(getApplicationContext(), "Add Feedback is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.addHrContact:
                        Intent intent6 = new Intent(ProfileActivity.this, AddHrContactActivity.class);
                        startActivity(intent6);
                        Toast.makeText(getApplicationContext(), "Add HR Contact is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.viewHrContact:
                        Intent intent7 = new Intent(ProfileActivity.this, ViewHRContact.class);
                        startActivity(intent7);
                        Toast.makeText(getApplicationContext(), "View HR Contact is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.viewPlacedStudent:
                        Intent intent8 = new Intent(ProfileActivity.this, ViewPlacedStudents.class);
                        startActivity(intent8);
                        Toast.makeText(getApplicationContext(), "View Placed Student is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.newQuiz:
                        Intent intent9 = new Intent(ProfileActivity.this, StudentQuizMainActivity.class);
                        startActivity(intent9);
                        Toast.makeText(getApplicationContext(), "Give Quiz is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.addQuestion:
                        Intent intent10 = new Intent(ProfileActivity.this, AdminCategoryActivity.class);
                        startActivity(intent10);
                        Toast.makeText(getApplicationContext(), "Add Question is open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        firebaseAuth.signOut();
                        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        Toast.makeText(getApplicationContext(), "Logged Out....", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();

                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logoutmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            firebaseAuth.signOut();
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Toast.makeText(this, "Logged Out....", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public String capitalizeName(String name) {
        String firstLetter = name.substring(0, 1);
        String remainingLetters = name.substring(1, name.length());
        firstLetter = firstLetter.toUpperCase();
        name = firstLetter + remainingLetters;
        return name;
    }
}