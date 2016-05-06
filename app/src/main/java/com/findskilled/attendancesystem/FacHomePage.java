package com.findskilled.attendancesystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;

public class FacHomePage extends AppCompatActivity {
ListView lv;
    ArrayList<String> list = new ArrayList<>();
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_home_page);
        Intent m = getIntent();
        id = m.getStringExtra("fac_id");
        lv=(ListView)findViewById(R.id.listitem);
        list.add("Manage Branch");
        list.add("Take Attendance");
        lv.setAdapter(new ArrayAdapter<String>(FacHomePage.this, android.R.layout.simple_list_item_1, list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              if(position==0)
              {
                  Intent main = new Intent(FacHomePage.this,AddBranch.class);
                  main.putExtra("fac_id",id);
                  startActivity(main);
              }
                else
              {
                  Intent main = new Intent(FacHomePage.this,TakeAttendance.class);
                  startActivity(main);
              }
            }
        });

    }
}
