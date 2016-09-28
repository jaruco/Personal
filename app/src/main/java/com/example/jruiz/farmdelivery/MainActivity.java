package com.example.jruiz.farmdelivery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextPosition;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button buttonAdd;
    private Button buttonView;
    private Button buttonVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextPosition = (EditText) findViewById(R.id.editTextPosition);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);
        buttonVer = (Button) findViewById(R.id.buttonVer);

        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        buttonVer.setOnClickListener(this);
    }

    private void addUser(){

        final String name = editTextName.getText().toString().trim();
        final String lastname = editTextLastName.getText().toString().trim();
        final String position = editTextPosition.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        class AddUser extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                editTextName.setText("");
                editTextLastName.setText("");
                editTextPosition.setText("");
                editTextEmail.setText("");
                editTextPassword.setText("");
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_USER_EMAIL,email);
                params.put(Config.KEY_USER_PASSWORD,password);
                params.put(Config.KEY_USER_NAME,name);
                params.put(Config.KEY_USER_LASTNAME,lastname);
                params.put(Config.KEY_USER_POSITION,position);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddUser au = new AddUser();
        au.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addUser();
        }

        if(v == buttonView){
            startActivity(new Intent(this, ViewAllUsers.class));
        }

        if(v == buttonVer){
            startActivity(new Intent(this, NewSubscription.class));
        }
    }
}