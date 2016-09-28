package com.example.jruiz.farmdelivery;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ViewUser extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextPosition;
    private EditText editTextEmail;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.USR_EMAIL);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextPosition = (EditText) findViewById(R.id.editTextPosition);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextEmail.setText(id);

        getUser();
    }

    private void getUser(){
        class GetUser extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewUser.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showUser(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_USER,id);
                return s;
            }
        }
        GetUser gu = new GetUser();
        gu.execute();
    }

    private void showUser(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);

            String email    = c.getString(Config.TAG_EMAIL);
            String name     = c.getString(Config.TAG_NAME);
            String lastname = c.getString(Config.TAG_LASTNAME);
            String position = c.getString(Config.TAG_POSITION);

            editTextEmail.setText(email);
            editTextName.setText(name);
            editTextLastName.setText(lastname);
            editTextPosition.setText(position);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateUser(){
        final String email    = editTextEmail.getText().toString().trim();
        final String name     = editTextName.getText().toString().trim();
        final String lastname = editTextLastName.getText().toString().trim();
        final String position = editTextPosition.getText().toString().trim();

        class UpdateUser extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewUser.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewUser.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_USER_EMAIL,email);
                hashMap.put(Config.KEY_USER_NAME,name);
                hashMap.put(Config.KEY_USER_LASTNAME,lastname);
                hashMap.put(Config.KEY_USER_POSITION,position);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_USER,hashMap);

                return s;
            }
        }

        UpdateUser ue = new UpdateUser();
        ue.execute();
    }

    private void deleteUser(){
        class DeleteUser extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewUser.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewUser.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_USER, id);
                return s;
            }
        }

        DeleteUser de = new DeleteUser();
        de.execute();
    }

    private void confirmDeleteUser(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this user?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteUser();
                        startActivity(new Intent(ViewUser.this,ViewAllUsers.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateUser();
        }

        if(v == buttonDelete){
            confirmDeleteUser();
        }
    }
}