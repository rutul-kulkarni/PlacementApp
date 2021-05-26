package com.example.rcpittnp.Admin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rcpittnp.Model.Criteria;
import com.example.rcpittnp.Model.Notice;
import com.example.rcpittnp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCampusNotice extends AppCompatActivity {
    Button addNotice;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference rootRef;
    String userId;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_campus_notice);

        firebaseAuth = FirebaseAuth.getInstance();

        EditText companyNameEt, dateEt, pkgEt, sscMarksEt, hscMarksEt, diplomaMarksEt, cgpaEt, activeBacklogEt, yearGapEt;
        ToggleButton criteriaTg, activeBacklogTg, yearGapTg;
        LinearLayout criteriaLayout;
        companyNameEt = findViewById(R.id.companyNameEt);
        dateEt = findViewById(R.id.dateEt);
        pkgEt = findViewById(R.id.pkgEt);
        sscMarksEt = findViewById(R.id.sscmarks);
        hscMarksEt = findViewById(R.id.hscmarks);
        diplomaMarksEt = findViewById(R.id.diplomamarks);
        cgpaEt = findViewById(R.id.cgpa);
        activeBacklogEt = findViewById(R.id.activeBacklogCount);
        yearGapEt = findViewById(R.id.yearGapCount);
        criteriaTg = findViewById(R.id.hasCriteria);
        activeBacklogTg = findViewById(R.id.activeBacklogAllowed);
        yearGapTg = findViewById(R.id.yearGapAllowed);
        criteriaLayout = findViewById(R.id.criterialayout);
        if (criteriaTg.isChecked()) {
            criteriaLayout.setVisibility(View.VISIBLE);
            if (yearGapTg.isChecked())
                yearGapEt.setVisibility(View.VISIBLE);
            if (activeBacklogTg.isChecked())
                activeBacklogEt.setVisibility(View.VISIBLE);
        }
        criteriaTg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    criteriaLayout.setVisibility(View.VISIBLE);
                else
                    criteriaLayout.setVisibility(View.GONE);
            }
        });
        yearGapTg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    yearGapEt.setVisibility(View.VISIBLE);
                else
                    yearGapEt.setVisibility(View.GONE);
            }
        });
        activeBacklogTg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    activeBacklogEt.setVisibility(View.VISIBLE);
                else
                    activeBacklogEt.setVisibility(View.GONE);
            }
        });
        Button yesBtn = findViewById(R.id.btnYes);
        Button cancelBtn = findViewById(R.id.btnCancel);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String companyName = "", date = "", pkg = "", sscMarks = "", hscMarks = "", diplomaMarks = "", cgpa = "", activeBacklogCount = "", yearGapCount = "";
                if (!TextUtils.isEmpty(companyNameEt.getText().toString()))
                    companyName = companyNameEt.getText().toString();
                if (!TextUtils.isEmpty(dateEt.getText().toString()))
                    date = dateEt.getText().toString();
                if (!TextUtils.isEmpty(pkgEt.getText().toString()))
                    pkg = pkgEt.getText().toString();
                if (!TextUtils.isEmpty(sscMarksEt.getText().toString()))
                    sscMarks = sscMarksEt.getText().toString();
                if (!TextUtils.isEmpty(hscMarksEt.getText().toString()))
                    hscMarks = hscMarksEt.getText().toString();
                if (!TextUtils.isEmpty(diplomaMarksEt.getText().toString()))
                    diplomaMarks = diplomaMarksEt.getText().toString();
                if (!TextUtils.isEmpty(cgpaEt.getText().toString()))
                    cgpa = cgpaEt.getText().toString();
                if (activeBacklogTg.isChecked() && !TextUtils.isEmpty(activeBacklogEt.getText().toString()))
                    activeBacklogCount = activeBacklogEt.getText().toString();
                if (yearGapTg.isChecked() && !TextUtils.isEmpty(yearGapEt.getText().toString()))
                    yearGapCount = yearGapEt.getText().toString();
                Criteria criteria = new Criteria(sscMarks, hscMarks, diplomaMarks, yearGapTg.isChecked(), yearGapCount, activeBacklogTg.isChecked(), activeBacklogCount, cgpa);
                Notice notice = new Notice(companyName, criteria, date, pkg);
                addDataToDataBase(notice);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void openCreateNoticeDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        /*dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));*/
        LayoutInflater inflater = LayoutInflater.from(this);
        View mainView = inflater.inflate(R.layout.add_notice_dialog, null);
        dialog.setContentView(mainView);
        EditText companyNameEt, dateEt, pkgEt, sscMarksEt, hscMarksEt, diplomaMarksEt, cgpaEt, activeBacklogEt, yearGapEt;
        ToggleButton criteriaTg, activeBacklogTg, yearGapTg;
        LinearLayout criteriaLayout;
        companyNameEt = mainView.findViewById(R.id.companyNameEt);
        dateEt = mainView.findViewById(R.id.dateEt);
        pkgEt = mainView.findViewById(R.id.pkgEt);
        sscMarksEt = mainView.findViewById(R.id.sscmarks);
        hscMarksEt = mainView.findViewById(R.id.hscmarks);
        diplomaMarksEt = mainView.findViewById(R.id.diplomamarks);
        cgpaEt = mainView.findViewById(R.id.cgpa);
        activeBacklogEt = mainView.findViewById(R.id.activeBacklogCount);
        yearGapEt = mainView.findViewById(R.id.yearGapCount);
        criteriaTg = mainView.findViewById(R.id.hasCriteria);
        activeBacklogTg = mainView.findViewById(R.id.activeBacklogAllowed);
        yearGapTg = mainView.findViewById(R.id.yearGapAllowed);
        criteriaLayout = mainView.findViewById(R.id.criterialayout);
        if (criteriaTg.isChecked()) {
            criteriaLayout.setVisibility(View.VISIBLE);
            if (yearGapTg.isChecked())
                yearGapEt.setVisibility(View.VISIBLE);
            if (activeBacklogTg.isChecked())
                activeBacklogEt.setVisibility(View.VISIBLE);
        }
        criteriaTg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    criteriaLayout.setVisibility(View.VISIBLE);
                else
                    criteriaLayout.setVisibility(View.GONE);
            }
        });
        yearGapTg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    yearGapEt.setVisibility(View.VISIBLE);
                else
                    yearGapEt.setVisibility(View.GONE);
            }
        });
        activeBacklogTg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    activeBacklogEt.setVisibility(View.VISIBLE);
                else
                    activeBacklogEt.setVisibility(View.GONE);
            }
        });
        Button yesBtn = mainView.findViewById(R.id.btnYes);
        Button cancelBtn = mainView.findViewById(R.id.btnCancel);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String companyName = "", date = "", pkg = "", sscMarks = "", hscMarks = "", diplomaMarks = "", cgpa = "", activeBacklogCount = "", yearGapCount = "";
                if (!TextUtils.isEmpty(companyNameEt.getText().toString()))
                    companyName = companyNameEt.getText().toString();
                if (!TextUtils.isEmpty(dateEt.getText().toString()))
                    date = dateEt.getText().toString();
                if (!TextUtils.isEmpty(pkgEt.getText().toString()))
                    pkg = pkgEt.getText().toString();
                if (!TextUtils.isEmpty(sscMarksEt.getText().toString()))
                    sscMarks = sscMarksEt.getText().toString();
                if (!TextUtils.isEmpty(hscMarksEt.getText().toString()))
                    hscMarks = hscMarksEt.getText().toString();
                if (!TextUtils.isEmpty(diplomaMarksEt.getText().toString()))
                    diplomaMarks = diplomaMarksEt.getText().toString();
                if (!TextUtils.isEmpty(cgpaEt.getText().toString()))
                    cgpa = cgpaEt.getText().toString();
                if (activeBacklogTg.isChecked() && !TextUtils.isEmpty(activeBacklogEt.getText().toString()))
                    activeBacklogCount = activeBacklogEt.getText().toString();
                if (yearGapTg.isChecked() && !TextUtils.isEmpty(yearGapEt.getText().toString()))
                    yearGapCount = yearGapEt.getText().toString();
                Criteria criteria = new Criteria(sscMarks, hscMarks, diplomaMarks, yearGapTg.isChecked(), yearGapCount, activeBacklogTg.isChecked(), activeBacklogCount, cgpa);
                Notice notice = new Notice(companyName, criteria, date, pkg);
                addDataToDataBase(notice);
                dialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void addDataToDataBase(Notice notice) {
        ProgressDialog loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Creating Notice");
        loadingBar.setMessage("Please wait...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        rootRef = FirebaseDatabase.getInstance().getReference().child("notices");
        rootRef = rootRef.push();
        rootRef.setValue(notice).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddCampusNotice.this, "Notice Created!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddCampusNotice.this, "Error creating notice!", Toast.LENGTH_SHORT).show();
                }
                loadingBar.dismiss();
            }
        });
    }
}