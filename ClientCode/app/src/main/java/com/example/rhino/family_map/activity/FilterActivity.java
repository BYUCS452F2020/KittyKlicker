package com.example.rhino.family_map.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.rhino.family_map.R;
import com.example.rhino.family_map.task.FilterAdapter;

import java.util.Map;

import model.client.Client;

public class FilterActivity extends AppCompatActivity {

    private Client client;

    private RecyclerView filterRecyclerView;
    private FilterAdapter filterAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        client = Client.getClient();

        filterRecyclerView = (RecyclerView) findViewById(R.id.filter_recycler);
        filterRecyclerView.setHasFixedSize(true);
        filterRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        filterAdapter = new FilterAdapter(getFilters(), FilterActivity.this);

        filterRecyclerView.setItemAnimator(new DefaultItemAnimator());
        filterRecyclerView.setAdapter(filterAdapter);
        filterAdapter.notifyDataSetChanged();
    }

    private Map.Entry<String, Boolean>[] getFilters()
    {
        for (Map.Entry<String, EventResponse> e : client.getAllEvents().entrySet())
        {
            if (client.getFilters().get(e.getValue().getEventType().toLowerCase()) == null)
            {
                client.getFilters().put(e.getValue().getEventType().toLowerCase(), true);
            }
        }
        return client.getFilters().entrySet().toArray(new Map.Entry[client.getFilters().size()]);
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
