package com.example.rcpittnp.ResumeModule;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rcpittnp.R;

public class ReferencesActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText refName,refOrg,refContact;
    Button insertRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);
        Intent intent = getIntent();

        db = openOrCreateDatabase("ResumeDB", Context.MODE_PRIVATE, null);

        refName = (EditText) findViewById(R.id.et1);
        refOrg = (EditText) findViewById(R.id.et2);
        refContact = (EditText) findViewById(R.id.et3);
        insertRef = (Button) findViewById(R.id.b1);

        insertRef.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            //Course VARCHAR,Institute VARCHAR,Score VARCHAR,Yop VARCHAR
            public void onClick(View view) {

                //Toast.makeText(References.this,PersonalInfo.Username.getText().toString(), Toast.LENGTH_LONG).show();
                Cursor c=db.rawQuery("SELECT * FROM PersonalDet7 WHERE Name ='"+ PersonalInfoActivity.Username.getText().toString() +"'", null);
                if(c.moveToFirst()) {
                    db.execSQL("UPDATE PersonalDet7 SET RefName ='"+ refName.getText()+"', RefOrg='"+ refOrg.getText()+"', RefContact='"+ refContact.getText()+"' WHERE Name ='"+PersonalInfoActivity.Username.getText().toString()+"'");
                    Toast.makeText(ReferencesActivity.this, "Data saved sucessfully!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(ReferencesActivity.this, "Data not saved!", Toast.LENGTH_LONG).show();
                }


            }
        });


    }
}