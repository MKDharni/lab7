package com.example.androidlabs;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class TestToolbar extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    String overflowToast = "You clicked on the overflow menu";
    Toolbar toolbar;

    DrawerLayout drawerLayout;
    private NavigationView nv;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {

            case R.id.one:
                Toast.makeText(this, overflowToast, Toast.LENGTH_LONG).show();
                break;
            case R.id.two:
                Toast.makeText(this, "You clicked on item 1", Toast.LENGTH_LONG).show();
                break;
            case R.id.three:
                Toast.makeText(this, "You clicked on item 2", Toast.LENGTH_LONG).show();
                break;
            case R.id.four:
                Toast.makeText(this, "You clicked on item 3", Toast.LENGTH_LONG).show();
                break;
  }



        return true;
    }

    public boolean onNavigationItemSelected( MenuItem item) {

        String message = null;

        switch(item.getItemId())
        {case R.id.ChatItem:
                Intent ChatPage = new Intent(this, ChatRoomActivity.class);
                startActivity(ChatPage);
                break;
            case R.id.WeatherItem:
                Intent WeatherPage = new Intent(this, WeatherForcast.class);
                startActivity(WeatherPage);
                break;
            case R.id.GoBack:
                Intent endit = new Intent(this, ProfileActivity.class);
                setResult(500,endit);
                finish();
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


}
