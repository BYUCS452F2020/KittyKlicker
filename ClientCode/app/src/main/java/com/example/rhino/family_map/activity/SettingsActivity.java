package com.example.rhino.family_map.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.rhino.family_map.R;
import com.example.rhino.family_map.task.GetInfoTask;
import com.google.android.gms.maps.GoogleMap;

import java.util.Vector;

import model.client.Client;

public class SettingsActivity extends AppCompatActivity implements GetInfoTask.GetInfoListener {

    private Spinner lifeStoryLineColor;
    private Spinner spouseLineColor;
    private Spinner familyTreeLineColor;
    private Spinner mapType;
    private Switch lifeSwitch;
    private Switch spouseSwitch;
    private Switch familySwitch;
    private LinearLayout resync;
    private LinearLayout logout;
    private static final String[] colors = {"Red", "Green", "Yellow", "Black"};
    private static final String[] mapTypes = {"Normal", "Satellite", "Terrain", "Hybrid"};
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        client = Client.getClient();

        lifeStoryLineColor = (Spinner)findViewById(R.id.life_story_lines_color);
        ArrayAdapter<String> lifeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, colors);

        lifeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lifeStoryLineColor.setAdapter(lifeAdapter);
        lifeStoryLineColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String selectedColor = (String)parent.getItemAtPosition(position);
                switch (selectedColor)
                {
                    case "Red": client.setLifeStoryLinesColor("red"); break;
                    case "Green": client.setLifeStoryLinesColor("green"); break;
                    case "Yellow": client.setLifeStoryLinesColor("yellow"); break;
                    case "Black": client.setLifeStoryLinesColor("black"); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Auto-generated method stub
            }
        });

        spouseLineColor = (Spinner)findViewById(R.id.spouse_lines_color);
        ArrayAdapter<String> spouseAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, colors);

        spouseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spouseLineColor.setAdapter(spouseAdapter);
        spouseLineColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String selectedColor = (String)parent.getItemAtPosition(position);
                switch (selectedColor)
                {
                    case "Red": client.setSpouseLinesColor("red"); break;
                    case "Green": client.setSpouseLinesColor("green"); break;
                    case "Yellow": client.setSpouseLinesColor("yellow"); break;
                    case "Black": client.setSpouseLinesColor("black"); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Auto-generated method stub
            }
        });

        familyTreeLineColor = (Spinner)findViewById(R.id.family_tree_lines_color);
        ArrayAdapter<String> treeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, colors);

        treeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyTreeLineColor.setAdapter(treeAdapter);
        familyTreeLineColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String selectedColor = (String)parent.getItemAtPosition(position);
                switch (selectedColor)
                {
                    case "Red": client.setFamilyTreeLinesColor("red"); break;
                    case "Green": client.setFamilyTreeLinesColor("green"); break;
                    case "Yellow": client.setFamilyTreeLinesColor("yellow"); break;
                    case "Black": client.setFamilyTreeLinesColor("black"); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Auto-generated method stub
            }
        });

        lifeSwitch = (Switch) findViewById(R.id.life_story_lines_switch);
        lifeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                client.setLifeSwitch(isChecked);
            }
        });

        spouseSwitch = (Switch) findViewById(R.id.spouse_lines_switch);
        spouseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                client.setSpouseSwitch(isChecked);
            }
        });

        familySwitch = (Switch) findViewById(R.id.family_tree_lines_switch);
        familySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                client.setFamilySwitch(isChecked);
            }
        });

        mapType = (Spinner)findViewById(R.id.map_type);
        ArrayAdapter<String> mapAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mapTypes);

        mapAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapType.setAdapter(mapAdapter);
        mapType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String selectedColor = (String)parent.getItemAtPosition(position);
                switch (selectedColor)
                {
                    case "Normal": client.setMapType(GoogleMap.MAP_TYPE_NORMAL); break;
                    case "Hybrid": client.setMapType(GoogleMap.MAP_TYPE_HYBRID); break;
                    case "Satellite": client.setMapType(GoogleMap.MAP_TYPE_SATELLITE); break;
                    case "Terrain": client.setMapType(GoogleMap.MAP_TYPE_TERRAIN); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Auto-generated method stub
            }
        });

        resync = (LinearLayout) findViewById(R.id.settings_resync);
        resync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetInfoTask getInfoTask = new GetInfoTask(SettingsActivity.this);
                getInfoTask.execute(client.getAuth());
                Toast.makeText(SettingsActivity.this, "Initiating Data Re-sync", Toast.LENGTH_LONG).show();
            }
        });

        logout = (LinearLayout) findViewById(R.id.settings_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.clear();
                Toast.makeText(SettingsActivity.this, "Good Bye", Toast.LENGTH_LONG).show();
                MainActivity.startTopActivity(SettingsActivity.this, true);
            }
        });

        updateUI();
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

    public void onGetInfoComplete(Pair<PersonsResponse, EventsResponse> info)
    {
        if (info.first.getMessage() != null)
        {
            Toast.makeText(SettingsActivity.this, info.first.getMessage(), Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (info.second.getMessage() != null)
            {
                Toast.makeText(SettingsActivity.this, info.second.getMessage(), Toast.LENGTH_SHORT).show();
            }
            else
            {
                // clear out client data for resync
                client.clear();

                // repopulate
                for (PersonResponse p : info.first.getPersons())
                {
                    client.getPersons().put(p.getPersonID(), p);
                    if (client.getChildren().get(p.getFather()) == null)
                        client.getChildren().put(p.getFather(), new Vector<PersonResponse>());
                    client.getChildren().get(p.getFather()).add(p);
                    if (client.getChildren().get(p.getMother()) == null)
                        client.getChildren().put(p.getMother(), new Vector<PersonResponse>());
                    client.getChildren().get(p.getMother()).add(p);
                }
                for (EventResponse e : info.second.getEvents())
                {
                    client.getAllEvents().put(e.getEventID(), e);
                    if (client.getPersonsEvents().get(e.getPersonID()) == null)
                        client.getPersonsEvents().put(e.getPersonID(), new Vector<EventResponse>());
                    client.getPersonsEvents().get(e.getPersonID()).add(e);
                }
                client.sort();

                Toast.makeText(SettingsActivity.this,
                        "Re-sync SUCCESS, "
                                + client.getPersons().get(client.getPersonID()).getFirstName()
                                + " "
                                + client.getPersons().get(client.getPersonID()).getLastName(),
                        Toast.LENGTH_LONG).show();
                MainActivity.startTopActivity(SettingsActivity.this, false);
            }
        }
    }

    private void updateUI()
    {
        String selectedColor = client.getLifeStoryLinesColor().toLowerCase();
        switch (selectedColor)
        {
            case "red":
                lifeStoryLineColor.setSelection(0);
                break;
            case "green":
                lifeStoryLineColor.setSelection(1);
                break;
            case "yellow":
                lifeStoryLineColor.setSelection(2);
                break;
            case "black":
                lifeStoryLineColor.setSelection(3);
                break;
        }

        selectedColor = client.getSpouseLinesColor().toLowerCase();
        switch (selectedColor)
        {
            case "red":
                spouseLineColor.setSelection(0);
                break;
            case "green":
                spouseLineColor.setSelection(1);
                break;
            case "yellow":
                spouseLineColor.setSelection(2);
                break;
            case "black":
                spouseLineColor.setSelection(3);
                break;
        }

        selectedColor = client.getFamilyTreeLinesColor().toLowerCase();
        switch (selectedColor)
        {
            case "red":
                familyTreeLineColor.setSelection(0);
                break;
            case "green":
                familyTreeLineColor.setSelection(1);
                break;
            case "yellow":
                familyTreeLineColor.setSelection(2);
                break;
            case "black":
                familyTreeLineColor.setSelection(3);
                break;
        }

        lifeSwitch.setChecked(client.isLifeSwitch());
        spouseSwitch.setChecked(client.isSpouseSwitch());
        familySwitch.setChecked(client.isFamilySwitch());

        // enum values of GoogleMap.MAP_TYPE equal position + 1
        mapType.setSelection(client.getMapType() - 1);
    }
}
