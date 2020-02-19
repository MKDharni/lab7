package com.example.androidlabs;
/**
//startActivity
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";
    ImageButton mImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(ACTIVITY_NAME,"In function : onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //getIntent() is to get the information from the previous page , MainActivity in here.
        Intent fromMain = getIntent();
        //getStringExtra , getIntExtra can be used.
        String email=fromMain.getStringExtra("EMAIL");

        EditText emailField=findViewById(R.id.editText2);
        emailField.setText(email);

        mImageButton=(ImageButton)findViewById(R.id.imageButton);
        mImageButton.setOnClickListener(btn->dispatchTakePictureIntent());

        Button goToChat = findViewById(R.id.goToChatButton);
        goToChat.setOnClickListener(e->{
            Intent goToChatActivity = new Intent(this, ChatRoomActivity.class);
            startActivity(goToChatActivity);
        });
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    //onActivityResult is to to be called by the function in nextPage to get the infor. back using startActivityForResult()
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(ACTIVITY_NAME,"In function : onActivityResult()");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onStart(){
        Log.e(ACTIVITY_NAME,"In function : onStart)");
        super.onStart();
    }

    @Override
    protected void onResume(){
        Log.e(ACTIVITY_NAME,"In function : onResume()");
        super.onResume();
    }

    @Override
    protected void onPause(){
        Log.e(ACTIVITY_NAME,"In function : onPause()");
        super.onPause();
    }

    @Override
    protected void onStop(){
        Log.e(ACTIVITY_NAME,"In function : onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        Log.e(ACTIVITY_NAME,"In function : onDestroy()");
        super.onDestroy();
    }
}**/


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageButton takePictureBtn;
    Button goToChatBtn;
    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";
////////////////
    @Override
    public Intent getIntent() {
        return super.getIntent();
    }
////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // get the intent that got us here
        Intent loginPage = getIntent();

        String emailTyped = loginPage.getStringExtra("emailTyped");

        //Put the string that was sent from FirstActivity into the edit text:
        EditText enterText = (EditText) findViewById(R.id.editText2);
        enterText.setText(emailTyped);

        takePictureBtn = (ImageButton) findViewById(R.id.imageButton);
        takePictureBtn.setOnClickListener(c -> {


            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

        });

        goToChatBtn = (Button)findViewById(R.id.goToChatButton);
        goToChatBtn.setOnClickListener(c -> {
            Intent goToChatPage = new Intent(ProfileActivity.this, ChatRoomActivity.class);

            startActivityForResult(goToChatPage, 345);

        });


        Log.d(ACTIVITY_NAME, "In function: onCreate()");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            takePictureBtn.setImageBitmap(imageBitmap);
        }
        Log.d(ACTIVITY_NAME, "In function: onActivityResult()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(ACTIVITY_NAME, "In function: onStart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(ACTIVITY_NAME, "In function: onResume()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ACTIVITY_NAME, "In function: onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(ACTIVITY_NAME, "In function: onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ACTIVITY_NAME, "In function: onDestroy()");
    }
}