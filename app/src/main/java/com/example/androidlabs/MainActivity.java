package com.example.androidlabs;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Button;
import com.google.android.material.snackbar.Snackbar;


import static com.google.android.material.snackbar.Snackbar.*;

public class MainActivity extends AppCompatActivity  {


    //private Object Switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main_relative);
        setContentView(R.layout.activity_main_grid);
        // setContentView(R.layout.activity_main_linear);
        Switch sb = new Switch(this);
        sb.setTextOff("OFF");
        sb.setTextOn("ON");
        sb.setChecked(true);

        CheckBox ch = findViewById(R.id.checkBox);

        EditText theEdit = findViewById(R.id.editText);

        Switch sw = findViewById(R.id.switch1);


        ch.setOnCheckedChangeListener((compoundButton,b) -> {


            String str;
            if (ch.isChecked()) {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.toast_message), Toast.LENGTH_LONG).show();
                str = sw.getTextOn().toString();
            }
            else {
                str = sw.getTextOff().toString();
            }
            Snackbar.make(ch, getResources().getString(R.string.toast_message2) +  " " +str, Snackbar.LENGTH_LONG)
                    .setAction("Undo", click-> compoundButton.setChecked( !b ))
                    .show();
        });

        sw.setOnCheckedChangeListener((compoundButton, b) -> {
            //Toast.makeText(MainActivity.this, "Here is more information.", Toast.LENGTH_LONG).show();

            String str1;
            if (sw.isChecked())
                str1 = sw.getTextOn().toString();
            else
                str1 = sw.getTextOff().toString();

            Snackbar.make(theEdit, getResources().getString(R.string.toast_message1) + " "+ str1, LENGTH_LONG)
                    .setAction("Undo", click -> compoundButton.setChecked(!b))
                    .show();


        });
    }}

//reference: for snackbar
//https://www.tutlane.com/tutorial/android/android-switch-on-off-button-with-examples

/**
 *
 * To make changes in toast
 *
 * if (b){
 Toast.makeText(MainActivity.this, "Checkbox is ticked.", Toast.LENGTH_LONG).show();
 } else {
 Toast.makeText(MainActivity.this, "Checkbox is not ticked.", Toast.LENGTH_LONG).show();
 }**/