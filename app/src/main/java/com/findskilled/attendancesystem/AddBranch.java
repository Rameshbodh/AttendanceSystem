package com.findskilled.attendancesystem;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AddBranch extends AppCompatActivity {
ListView lvs;
ArrayList<String> list=new ArrayList<>();
    String branchname;
    Integer id;
    private Button add;
    private RadioButton branch;
    private RadioGroup select_branch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

        Intent m = getIntent();
        id = m.getIntExtra("fac_id",-1);
        Toast.makeText(AddBranch.this, " OK "+id, Toast.LENGTH_SHORT).show();

        add=(Button)findViewById(R.id.add_branch);
        select_branch=(RadioGroup)findViewById(R.id.radioGroup);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId=select_branch.getCheckedRadioButtonId();
                branch=(RadioButton)findViewById(selectedId);

                if(branch.getText().equals("Computer Science"))
                {
                   branchname = branch.getText().toString();
                    Connect con = new Connect();
                    con.execute();
                }
                else if(branch.getText().equals("Mechanical Engineering"))
                {
                    branchname = branch.getText().toString();
                    Connect con = new Connect();
                    con.execute();
                }
                else if(branch.getText().equals("Electrical Engineering"))
                {
                    branchname = branch.getText().toString();
                    Connect con = new Connect();
                    con.execute();
                }
                else if(branch.getText().equals("Electronics Communication Engineering"))
                {
                    branchname = branch.getText().toString();
                    Connect con = new Connect();
                    con.execute();
                }
                else if(branch.getText().equals("Civil Engineering"))
                {
                    branchname = branch.getText().toString();
                    Connect con = new Connect();
                    con.execute();
                }
                else if(branch.getText().equals("Automobile Engineering"))
                {
                    branchname = branch.getText().toString();
                    Connect con = new Connect();
                    con.execute();
                }

            }
        });

             }

             class Connect extends AsyncTask<Void, Void, Void> {
                 String ide;
                 SweetAlertDialog alert;

                 @Override
                 protected void onPreExecute() {
                     super.onPreExecute();


                 }

                 @Override
                 protected void onPostExecute(Void aVoid) {
                     super.onPostExecute(aVoid);
                     //int fac = Integer.parseInt(fac_id);
                     Toast.makeText(AddBranch.this, " OK "+ide, Toast.LENGTH_SHORT).show();
                     if (ide == "0") {
                         new SweetAlertDialog(AddBranch.this)
                                 .setTitleText("Branch Name already exist  ..!")
                                 .show();

                     }
                     if (ide == "1") {

                         new SweetAlertDialog(AddBranch.this)
                                 .setTitleText("Branch Name Added Successfully  ..!")
                                 .show();


                     }
                     if (ide == "2") {
                         new SweetAlertDialog(AddBranch.this)
                                 .setTitleText("Can not add Branch Name ..!")
                                 .show();

                     }
                 }

                 @Override
                 protected Void doInBackground(Void... params) {
                     ServerReq req = new ServerReq();
                     String a;
                     try {
                         a = req.AddBranch(id, branchname);
                         JsonResponce(a);
                     } catch (Exception e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }

                     return null;
                 }

                 public void JsonResponce(String s) throws JSONException {


                     JSONObject json = new JSONObject(s);
                     JSONArray jarray = json.getJSONArray("user");


                     for (int i = 0; i <= jarray.length(); i++) {

                         JSONObject jobj = jarray.getJSONObject(i);


                         ide = jobj.getString("id");

                     }

                 }
             }


         }
