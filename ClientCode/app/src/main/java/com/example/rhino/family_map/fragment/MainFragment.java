package com.example.rhino.family_map.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhino.family_map.R;

import model.client.KittyClient;

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
//        username.setText(getUsername());
        teamName = view.findViewById(R.id.team);
//        teamName.setText(getTeamName());
        grumpyCat = view.findViewById(R.id.kitty_image);
        grumpyCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catClicked();
            }
        });
        score = view.findViewById(R.id.score);
//        score.setText(getScore());
        kittyPower = view.findViewById(R.id.kitty_power);
//        kittyPower.setText(getPower());
        powerups = view.findViewById(R.id.powerups);
//        powerups.setText(getPowerups());

        return view;
    }

    private void catClicked() {
        //cat was clicked... do things
    }
}
