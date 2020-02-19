package com.example.androidlabs;
/**
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs = null;
    String stringToSave="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText emailField=findViewById(R.id.editText1);
        SharedPreferences  prefs = getSharedPreferences("FileName", Context.MODE_PRIVATE);
        String savedString = prefs.getString("email", "");
        emailField.setText(savedString);
        Button loginButton = findViewById(R.id.button1);
        loginButton.setOnClickListener( bt ->{stringToSave=emailField.getText().toString();
        //Intent is an object that tells which page we are now and where to go next.
        Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
            //putExtra basically adds extra inf. to send to next page.
        goToProfile.putExtra("EMAIL",stringToSave);
            //to go to next page like profilePage here.
            startActivity(goToProfile);

        } );
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", stringToSave);
        editor.commit();
    }
}**/


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText emailField;
    SharedPreferences sp;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main3);

        emailField = (EditText)findViewById(R.id.editText1);
        sp = getSharedPreferences("FileName", Context.MODE_PRIVATE);
        String savedString = sp.getString("ReserveName", "Default value");

        emailField.setHint(savedString);

        loginBtn = (Button)findViewById(R.id.button1);

        loginBtn.setOnClickListener( c -> {

            Intent profilePage = new Intent(MainActivity.this, ProfileActivity.class);
            //Give directions to go from this page, to SecondActivity
            // EditText et = (EditText)findViewById(R.id.Lab3editText2);

            profilePage.putExtra("emailTyped", emailField.getText().toString());

            //Now make the transition:
            startActivityForResult( profilePage, 345);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //get an editor object
        SharedPreferences.Editor editor = sp.edit();

        //save what was typed under the name "ReserveName"
        String whatWasTyped = emailField.getText().toString();
        editor.putString("ReserveName", whatWasTyped);

        //write it to disk:
        editor.commit();
    }
}