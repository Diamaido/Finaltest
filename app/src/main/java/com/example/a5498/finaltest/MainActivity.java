package com.example.a5498.finaltest;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    TextView msg;
    ListView ls;
    Context c;
    Map<String,Integer> ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg = findViewById(R.id.MessageTextview);
        ls = findViewById(R.id.Datalist);
        c = this;

        ma = new HashMap<String, Integer>();

        Button b = findViewById(R.id.GetButton);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Getdata d = new Getdata();
                d.execute("");
            }
        });
    }


    private class Getdata extends AsyncTask<String, String,String>{


        protected  void onPreExecute(){
            msg.setText("Logging in.......");
        }


        @Override
        protected String doInBackground(String... strings) {

            Connection c = null;
            Statement s = null;
            ResultSet rs = null;

            try {

                Class.forName("com.mysql.jdbc.Driver");
                c = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/testdb", "root", "Ka930304");

                s = c.createStatement();
                rs = s.executeQuery("SELECT * FROM login");

                while (rs.next()) {
                    String st = rs.getString("Username");
                    int in =  rs.getInt("password");
                    ma.put(st,in);
                }

                c.close();
                s.close();
                rs.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
              return null;
        }


        @Override
        protected void onPostExecute(String ms){

            if(ma.size()>0){
            msg.setText("Setting Adapter ");
            ItemAdapter it = new ItemAdapter(c,ma);
            ls.setAdapter(it);
            msg.setText("Setting Adapter Complete");
        }}




    }








}
