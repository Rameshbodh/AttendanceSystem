package com.findskilled.attendancesystem;

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

import java.util.ArrayList;
import java.util.List;

public class AddSemester extends AppCompatActivity {
ListView branchList;
    ArrayList<String> list = new ArrayList<>();
    String fac_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);
        Intent m = getIntent();
       fac_id= m.getStringExtra("fac_id");
        branchList=(ListView)findViewById(R.id.listView2);
        Connect con =new Connect();
        con.execute();
        branchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String branch_id= list.get(position);




            }
        });

    }

    class Connect extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //int fac = Integer.parseInt(fac_id);
            branchList.setAdapter(new ArrayAdapter<String>(AddSemester.this,android.R.layout.simple_list_item_1,list));



        }

        @Override
        protected Void doInBackground(Void... params) {
            ServerReq req= new ServerReq();
            String a;
            try {
                a = req.BranchName(fac_id);
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


                list.add(jobj.getString("id"));
                list.add(jobj.getString("branch_name"));

            }

        }
    }


}
