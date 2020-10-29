package com.example.rhino.family_map.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rhino.family_map.R;
import com.example.rhino.family_map.fragment.LoginFragment;
import com.example.rhino.family_map.fragment.MapsFragment;

public class MainActivity extends AppCompatActivity {

    private static FragmentManager fragMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        fragMan = fm;
        Fragment fragment = fm.findFragmentById(R.id.main_fragment_container);

        if (fragment == null) {
            fragment = new LoginFragment();
            fm.beginTransaction()
                    .add(R.id.main_fragment_container, fragment)
                    .commit();
        }
    }

    public void onLoginComplete()
    {
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = new MapsFragment();
        fm.beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return false;    // return true to display menu
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                showToast("Search");
                startActivity(SearchActivity.class);
                return true;    // return true when handled successfully
            case R.id.settings:
                showToast("Settings");
                startActivity(SettingsActivity.class);
                return true;
            case R.id.filter:
                showToast("Filter");
                startActivity(FilterActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    public void startActivity(Class aClass) {
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }

    public static void startTopActivity(Context context, boolean newInstance) {
        Intent intent = new Intent(context, MainActivity.class);
        if (newInstance) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        else {
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        context.startActivity(intent);
    }
}
