package com.example.rcpittnp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcpittnp.Adapter.ViewNoticeRVAdapter;
import com.example.rcpittnp.Model.Criteria;
import com.example.rcpittnp.Model.Notice;

import java.util.ArrayList;
import java.util.List;

public class ViewNotice extends AppCompatActivity {
    RecyclerView viewNoticeRV;
    List<Notice> notices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notice);

        viewNoticeRV = findViewById(R.id.viewnoticeRV);
        notices = new ArrayList<>();
        addDataToNotice();
        ViewNoticeRVAdapter adapter = new ViewNoticeRVAdapter(notices , this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL,false);
        viewNoticeRV.setLayoutManager(layoutManager);
        viewNoticeRV.setAdapter(adapter);
    }

    private void addDataToNotice() {
        Criteria criteria = new Criteria("80","65","80",false,"0",false,"0","8.0");
        Criteria criteria1 = new Criteria("80","65","80",true,"1",true,"2","8.0");
        Criteria criteria2 = new Criteria("80","65","80",false,"0",true,"3","8.0");
        Criteria criteria3 = new Criteria();
        Notice notice = new Notice("Persistent" , criteria , "04/09/2020" , "4.51 lpa");
        Notice notice1 = new Notice("Capgemini" , criteria1 , "04/09/2020" , "3.8 lpa");
        Notice notice2 = new Notice("TCS" , criteria2 , "04/09/2020" , "3.36 lpa");
        Notice notice3 = new Notice("Infosys" , criteria3 , "04/09/2020" , "3.6 lpa");

        notices.add(notice);
        notices.add(notice1);
        notices.add(notice2);
        notices.add(notice3);
        notices.add(notice);
        notices.add(notice1);
        notices.add(notice2);
        notices.add(notice3);
        notices.add(notice);
        notices.add(notice1);
        notices.add(notice2);
        notices.add(notice3);
    }
}