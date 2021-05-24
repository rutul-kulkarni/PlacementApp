package com.example.rcpittnp.NewQuizModule;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rcpittnp.NewQuizModule.Adapter.StudentCatGridAdapter;
import com.example.rcpittnp.R;

import static com.example.rcpittnp.NewQuizModule.StudentQuizMainActivity.catList;

public class StudentCategoryActivity extends AppCompatActivity {

    private GridView catGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        catGrid = findViewById(R.id.catGridview);


        StudentCatGridAdapter adapter = new StudentCatGridAdapter(catList);
        catGrid.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            StudentCategoryActivity.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}