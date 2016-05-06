package com.findskilled.attendancesystem;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

        Intent m = getIntent();
     id =  m.getStringExtra("fac_id");


    lvs=(ListView)findViewById(R.id.listViews);
        list.add("Computer Science Engineering");
        list.add("Mechanical Engineering");
        list.add("Eletrical Engineering");
        list.add("Electronics Communication Engineering");
        list.add("Civil Engineering");
        list.add("Automobile Engineering");
lvs.setAdapter(new ArrayAdapter<String>(AddBranch.this, android.R.layout.simple_list_item_1, list));

        lvs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              branchname =list.get(position);



            }
        });
    }

    class Connect extends AsyncTask<Void,Void,Void>
    {
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
            if(ide=="0")
            {
                new SweetAlertDialog(AddBranch.this)
                        .setTitleText("Branch Name already exist  ..!")
                        .show();

            }
            if(ide=="1")
            {

                new SweetAlertDialog(AddBranch.this)
                        .setTitleText("Branch Name Added Successfully  ..!")
                        .show();



            }
            if(ide=="2")
            {
                new SweetAlertDialog(AddBranch.this)
                        .setTitleText("Can not add Branch Name ..!")
                        .show();

            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            ServerReq req= new ServerReq();
            String a;
            try {
                a = req.AddBranch(id,branchname);
                JsonResponce(a);
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }
        public void JsonResponce(String s) throws JSONException {



            JSONObject json=new JSONObject(s);
            JSONArray jarray=json.getJSONArray("user");


            for(int i=0;i<=jarray.length();i++)
            {

                JSONObject jobj =jarray.getJSONObject(i);


                ide=jobj.getString("id");

            }

        }
    }






}
