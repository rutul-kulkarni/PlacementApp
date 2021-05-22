package com.example.rcpittnp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rcpittnp.Model.CompanyFeedback;
import com.example.rcpittnp.Model.StudentModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddFeedbackActivity extends AppCompatActivity {
    DatabaseReference rootRef;
    FirebaseAuth firebaseAuth;
    ProgressDialog loadingBar;
    String userId;
    Spinner companySpinner;
    ArrayAdapter<String> companySpinnerAdapter;
    ArrayList<String> placedCompanies = new ArrayList<>();
    Button addQueBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        companySpinner = findViewById(R.id.companySpinner);
        addQueBtn = findViewById(R.id.btnAddQue);
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        rootRef = FirebaseDatabase.getInstance().getReference("users").child("student").child(userId);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StudentModel student = snapshot.getValue(StudentModel.class);
                placedCompanies = student.getPlacedCompanies();
                Log.d("Company", "onDataChange: "+placedCompanies);
                Toast.makeText(AddFeedbackActivity.this, "Data Fetched...!", Toast.LENGTH_SHORT).show();
                companySpinnerAdapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item, placedCompanies);
                companySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                companySpinner.setAdapter(companySpinnerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addQueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (companySpinner.getSelectedItemPosition()>0)
                {
                    String selectedCompany = companySpinner.getSelectedItem().toString();
                    createAddQuestonDialog(selectedCompany);
                }
            }
        });
    }

    void createAddQuestonDialog(String selectedCompany)
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.campus_feedback_dialog, null);
        dialog.setContentView(view);
        dialog.show();
        EditText que1Et,que2Et,que3Et,que4Et,que5Et,que6Et,que7Et,que8Et,que9Et,que10Et;
        que1Et = view.findViewById(R.id.que1);
        que2Et = view.findViewById(R.id.que2);
        que3Et = view.findViewById(R.id.que3);
        que4Et = view.findViewById(R.id.que4);
        que5Et = view.findViewById(R.id.que5);
        que6Et = view.findViewById(R.id.que6);
        que7Et = view.findViewById(R.id.que7);
        que8Et = view.findViewById(R.id.que8);
        que9Et = view.findViewById(R.id.que9);
        que10Et = view.findViewById(R.id.que10);

        final Button btnCancel = view.findViewById(R.id.btnCancel);
        final Button addBtn = view.findViewById(R.id.btnDone);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String que1 = "" ,que2= "" ,que3= "" ,que4= "" ,que5= "" ,que6= "" ,que7= "" ,que8= "" ,que9= "" ,que10= "" ;
                que1 = que1Et.getText().toString();
                que2 = que2Et.getText().toString();
                que3 = que3Et.getText().toString();
                que4 = que4Et.getText().toString();
                que5 = que5Et.getText().toString();
                que6 = que6Et.getText().toString();
                que7 = que7Et.getText().toString();
                que8 = que8Et.getText().toString();
                que9 = que9Et.getText().toString();
                que10 = que10Et.getText().toString();
                CompanyFeedback companyFeedback = new CompanyFeedback(que1 ,que2 ,que3 ,que4 ,que5 ,que6 ,que7 ,que8 ,que9 ,que10);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("feedback").child(userId).child(selectedCompany);
                ref.setValue(companyFeedback);
                Toast.makeText(AddFeedbackActivity.this, "Feedback Added...!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}