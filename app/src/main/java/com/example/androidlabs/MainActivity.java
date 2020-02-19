package com.example.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText emailField;
    SharedPreferences prefs;
    Button LOGINbUTTON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main3);
        emailField = (EditText)findViewById(R.id.editText1);
        prefs = getSharedPreferences("FileName", Context.MODE_PRIVATE);
        String savedString = prefs.getString("ReserveName", "Default value");
        emailField.setHint(savedString);
        LOGINbUTTON = (Button)findViewById(R.id.button1);
        LOGINbUTTON.setOnClickListener(c -> {
        Intent profilePage = new Intent(MainActivity.this, ProfileActivity.class);
        profilePage.putExtra("emailTyped", emailField.getText().toString());
            startActivityForResult( profilePage, 400);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = prefs.edit();
        String whatWasTyped = emailField.getText().toString();
        editor.putString("ReserveName", whatWasTyped);
        editor.commit();
    }
}