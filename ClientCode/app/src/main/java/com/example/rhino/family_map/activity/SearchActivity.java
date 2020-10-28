package com.example.rhino.family_map.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rhino.family_map.R;
import com.example.rhino.family_map.task.EventAdapter;
import com.example.rhino.family_map.task.SearchAdapter;

import java.util.Map;
import java.util.Vector;

import model.client.Client;

public class SearchActivity extends AppCompatActivity {

    private Client client;

    private EditText searchField;
    private Button searchButton;

    private RecyclerView searchRecyclerView;
    private SearchAdapter searchAdapter;

    private RecyclerView eventRecyclerView;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        client = Client.getClient();

        searchField = (EditText) findViewById(R.id.search_search_bar);

        searchButton = (Button) findViewById(R.id.search_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.setSearch(searchField.getText().toString().toLowerCase());

                searchRecyclerView = (RecyclerView) findViewById(R.id.search_person_recycler_view);
                searchRecyclerView.setHasFixedSize(true);
                searchRecyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

                searchAdapter = new SearchAdapter(getMatches(), SearchActivity.this);

                searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
                searchRecyclerView.setAdapter(searchAdapter);
                searchAdapter.notifyDataSetChanged();
            }
        });
    }

    private Vector<Object> getMatches()
    {
        Vector<Object> result = new Vector<>();
        result.addAll(getMatchingPeople());
        result.addAll(getMatchingEvents());
        return result;
    }

    private Vector<PersonResponse> getMatchingPeople()
    {
        Vector<PersonResponse> result = new Vector<>();
        if (client.getSearch().isEmpty() || client.getSearch() == null)
        {
            return result;
        }
        for (Map.Entry<String, PersonResponse> e : client.getPersons().entrySet())
        {
            if (e.getValue().getFirstName().toLowerCase().contains(client.getSearch().toLowerCase()))
            {
                result.add(e.getValue());
            }
            else if (e.getValue().getLastName().toLowerCase().contains(client.getSearch().toLowerCase()))
            {
                result.add(e.getValue());
            }
        }
        return result;
    }

    private Vector<EventResponse> getMatchingEvents()
    {
        Vector<EventResponse> result = new Vector<>();
        if (client.getSearch().isEmpty() || client.getSearch() == null)
        {
            return result;
        }
        for (Map.Entry<String, EventResponse> e : client.getAllEvents().entrySet())
        {
            if (!client.getFilters().get("Father's Side"))
            {
                if (client.getOnFatherSide().get(e.getValue().getPersonID()) != null)
                {
                    if (client.getOnFatherSide().get(e.getValue().getPersonID()))
                    {
                        continue;
                    }
                }
            }
            if (!client.getFilters().get("Mother's Side"))
            {
                if (client.getOnFatherSide().get(e.getValue().getPersonID()) != null)
                {
                    if (!client.getOnFatherSide().get(e.getValue().getPersonID()))
                    {
                        continue;
                    }
                }
            }
            if (client.getPersons().get(e.getValue().getPersonID()).getGender() == 'm')
            {
                if (!client.getFilters().get("Male"))
                {
                    continue;
                }
            }
            if (client.getPersons().get(e.getValue().getPersonID()).getGender() == 'f')
            {
                if (!client.getFilters().get("Female"))
                {
                    continue;
                }
            }
            if (client.getFilters().get(e.getValue().getEventType().toLowerCase()) != null)
            {
                if (!client.getFilters().get(e.getValue().getEventType().toLowerCase()))
                {
                    continue;
                }
            }

            if (e.getValue().getCountry().toLowerCase().contains(client.getSearch().toLowerCase()))
            {
                result.add(e.getValue());
            }
            else if (e.getValue().getCity().toLowerCase().contains(client.getSearch().toLowerCase()))
            {
                result.add(e.getValue());
            }
            else if (e.getValue().getEventType().toLowerCase().contains(client.getSearch().toLowerCase()))
            {
                result.add(e.getValue());
            }
            else if (String.valueOf(e.getValue().getYear()).toLowerCase().contains(client.getSearch().toLowerCase()))
            {
                result.add(e.getValue());
            }
        }
        return result;
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
