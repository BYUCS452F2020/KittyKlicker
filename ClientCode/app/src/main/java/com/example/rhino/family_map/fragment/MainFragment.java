package com.example.rhino.family_map.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhino.family_map.activity.MainActivity;
import com.example.rhino.family_map.activity.PersonActivity;
import com.example.rhino.family_map.R;

import java.util.Map;
import java.util.Vector;

import model.client.Client;
import model.client.KittyClient;
import model.server.EventResponse;
import model.server.PersonResponse;

public class MainFragment extends Fragment {

    private TextView username;
    private TextView teamName;
    private ImageView grumpyCat;
    private TextView score;
    private TextView kittyPower;
    private TextView powerups;
    private KittyClient client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        setHasOptionsMenu(false);
        client = KittyClient.getClient();

        username = view.findViewById(R.id.kitty_user);
        username.setText(client.getUserID());
        teamName = view.findViewById(R.id.team);
        teamName.setText(client.getTeamName());
        grumpyCat = view.findViewById(R.id.kitty_image);
        grumpyCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catClicked();
            }
        });
        score = view.findViewById(R.id.score);
        score.setText(client.getKittiesKlicked());
        kittyPower = view.findViewById(R.id.kitty_power);
        kittyPower.setText(client.getKittyPower());
        powerups = view.findViewById(R.id.powerups);
//        powerups.setText(client.getPowerups());

        return view;
    }

    private void catClicked() {
        //cat was clicked... do things
    }
}
