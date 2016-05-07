package com.findskilled.attendancesystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class FacHomePage extends AppCompatActivity {
ListView lv;
    ArrayList<String> list = new ArrayList<>();
    Button logout;
    Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_home_page);
        Intent m = getIntent();
        id = m.getIntExtra("fac_id",-1);
        lv=(ListView)findViewById(R.id.listitem);
        logout=(Button)findViewById(R.id.button2);
        list.add("Add Branch");
        list.add("Add Semester");
        list.add("Add Section");
        list.add("Add Student");
        list.add("Attendence");
        list.add("Notice");
        list.add("Change Password");
        Toast.makeText(FacHomePage.this, " Faculty ID " + id, Toast.LENGTH_SHORT).show();
        lv.setAdapter(new ArrayAdapter<String>(FacHomePage.this, android.R.layout.simple_list_item_1, list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent main = new Intent(FacHomePage.this, AddBranch.class);
                    main.putExtra("fac_id",id);
                    startActivity(main);
                } else if(position == 1) {
                    Intent main = new Intent(FacHomePage.this, AddSemester.class);
                    main.putExtra("fac_id", id);
                    startActivity(main);

                }
                else if(position == 2) {
                    Intent main = new Intent(FacHomePage.this, AddSection.class);
                    startActivity(main);
                }
                else if(position == 3) {
                    Intent main = new Intent(FacHomePage.this, AddStudent.class);
                    startActivity(main);
                }
                else if(position == 4) {
                    Intent main = new Intent(FacHomePage.this, Attendence.class);
                    startActivity(main);
                }
                else if(position == 5) {
                    Intent main = new Intent(FacHomePage.this, Notice.class);
                    startActivity(main);
                }
                else if(position == 6) {
                    Intent main = new Intent(FacHomePage.this, ChangePassword.class);
                    startActivity(main);
                }
            }
        });
logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
Intent main = new Intent(FacHomePage.this,FacMainPage.class);

        startActivity(main);
        }
});
    }
}
