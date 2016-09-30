package com.example.jruiz.farmdelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewAllUsers extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }


    private void showUser(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String email = jo.getString(Config.TAG_EMAIL);
                String name = jo.getString(Config.TAG_NAME);
                String lastname = jo.getString(Config.TAG_LASTNAME);
                String position = jo.getString(Config.TAG_POSITION);


                HashMap<String,String> users = new HashMap<>();
                users.put(Config.TAG_EMAIL,email);
                users.put(Config.TAG_NAME,name);
                users.put(Config.TAG_LASTNAME, lastname);
                users.put(Config.TAG_POSITION, position);

                list.add(users);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewAllUsers.this, list, R.layout.list_user,
                new String[]{Config.TAG_EMAIL,Config.TAG_NAME},
                new int[]{R.id.Email, R.id.Name});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewAllUsers.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showUser();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewUser.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String userEmail = map.get(Config.TAG_EMAIL).toString();
        intent.putExtra(Config.USR_EMAIL,userEmail);
        startActivity(intent);
    }
}