package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);
        DetailsFragment dFragment = new DetailsFragment();

        Bundle b = getIntent().getExtras();
        dFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmpty, dFragment).commit();


    }
}