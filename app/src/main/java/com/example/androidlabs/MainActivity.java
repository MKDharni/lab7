
        package com.example.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    //protected Button myButton;
    private EditText email;
    private EditText password;
    private Button login;
    public static final String ACTIVITY_NAME = "MainActivity";
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        email=(EditText)findViewById(R.id.editTextEmail);
        password=(EditText)findViewById(R.id.editTextPassword);
        login=(Button)findViewById(R.id.buttonLogin);
        sharedPreferences = getSharedPreferences("SharedPreferenceFile", Context.MODE_PRIVATE);
        String savedString = sharedPreferences.getString("emailAddress", "").toString();
        email.setText(savedString);



        login.setOnClickListener(e->
        {
            Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
            intent.putExtra("ReservedName",savedString);
            startActivity(intent);
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(ACTIVITY_NAME,"onPause");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("emailAddress", email.getText().toString());
        Log.e(ACTIVITY_NAME,"onPause"+email.getText().toString());
        editor.commit();
    }


}













