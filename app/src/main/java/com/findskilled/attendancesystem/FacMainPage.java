package com.findskilled.attendancesystem;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;



public class FacMainPage extends AppCompatActivity {
 EditText username,password;
    String un,pw;

    private Button login;
    private RadioButton l_type;
    private RadioGroup login_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_main_page);
        username=(EditText)findViewById(R.id.emailUN);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.loginbtn);
        login_type=(RadioGroup)findViewById(R.id.radioGroup);



            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int selectedId=login_type.getCheckedRadioButtonId();
                    l_type=(RadioButton)findViewById(selectedId);

                    if(l_type.getText().equals("Faculty"))
                    {
                        // Toast.makeText(FacMainPage.this, "  "+username.getText(), Toast.LENGTH_SHORT).show();
                        if(username.getText().toString().isEmpty()) {

                            username.setError("cannot be empty");

                        }
                        else if(password.getText().toString().isEmpty()) {

                            password.setError("cannot be empty");

                        }

                        else {
                            un = username.getText().toString();
                            pw = password.getText().toString();
                            Connect con = new Connect();
                            con.execute();

                        }
                    }
                    else if(l_type.getText().equals("Student"))
                    {
                        Toast.makeText(FacMainPage.this, "Hello", Toast.LENGTH_SHORT).show();
                    }

                }
            });












    }
    class Connect extends AsyncTask<Void,Void,Void>
    {
        String fac_id;


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
           int fac = Integer.parseInt(fac_id);

      //    Toast.makeText(FacMainPage.this,""+fac,Toast.LENGTH_LONG).show();
            if(fac==0)
            {
                username.setText("");
                password.setError("invalid password or email");

            }
            else
            {
                Intent m = new Intent(FacMainPage.this,FacHomePage.class);
                m.putExtra("fac_id",fac);
                startActivity(m);
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


            fac_id=jobj.getString("id").toString();

        }

           }
    }
}
