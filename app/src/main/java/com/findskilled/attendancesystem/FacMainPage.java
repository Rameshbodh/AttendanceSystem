package com.findskilled.attendancesystem;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;



public class FacMainPage extends AppCompatActivity {
 EditText username,password;
    String un,pw;
    String fac_id;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_main_page);
        username=(EditText)findViewById(R.id.emailUN);
        password=(EditText)findViewById(R.id.password);

        login=(Button)findViewById(R.id.loginbtn);

        un=username.getText().toString();
        pw=password.getText().toString();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(un!=null && pw!=null)
                {

                    Connect con=new Connect();
                    con.execute();

                }
                else
                {
                    username.setError("username can not be empty");
                    password.setError("password can not be empty");
                }




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
            if(fac_id!="0")
            {
                Intent m = new Intent(FacMainPage.this,FacHomePage.class);
                m.putExtra("fac_id",fac_id);
                startActivity(m);
            }
            else
            {

                username.setText("");
                password.setError("invalid password or email");
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
           ServerReq req= new ServerReq();
            String a;
            try {
                a = req.FacultyValidation(un,pw);
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


            fac_id=jobj.getString("id");

        }

           }
    }
}
