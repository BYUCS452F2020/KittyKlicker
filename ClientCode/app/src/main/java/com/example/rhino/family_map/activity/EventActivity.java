package com.example.rhino.family_map.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.rhino.family_map.R;
import com.example.rhino.family_map.fragment.LoginFragment;
import com.example.rhino.family_map.fragment.MapsFragment;

import model.client.Client;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_event);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.event_fragment_container);

        if (fragment == null) {
            fragment = new MapsFragment();
            fm.beginTransaction()
                    .add(R.id.event_fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            MainActivity.startTopActivity(this, false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
