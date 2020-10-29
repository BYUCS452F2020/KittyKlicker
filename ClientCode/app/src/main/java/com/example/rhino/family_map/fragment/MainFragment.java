package com.example.rhino.family_map.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import com.example.rhino.family_map.R;

=======
import com.example.rhino.family_map.activity.MainActivity;
import com.example.rhino.family_map.R;

import java.util.Map;
import java.util.Vector;

>>>>>>> 6d499f874322db750f64cfe45925dd5bf4b6ac41
import model.client.KittyClient;

public class MainFragment extends Fragment {

    private TextView username;
    private TextView teamName;
    private ImageView grumpyCat;
    private TextView score;
    private TextView kittyPower;
    private TextView powerups;
<<<<<<< HEAD
=======
    private ImageView p1;
    private ImageView p2;
    private ImageView p3;
    private ImageView p4;
    private ImageView p5;

>>>>>>> 6d499f874322db750f64cfe45925dd5bf4b6ac41
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
        p1 = view.findViewById(R.id.p1);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Power-up 1!");
            }
        });
        p2 = view.findViewById(R.id.p2);
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Power-up 2!");
            }
        });
        p3 = view.findViewById(R.id.p3);
        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Power-up 3!");
            }
        });
        p4 = view.findViewById(R.id.p4);
        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Power-up 4!");
            }
        });
        p5 = view.findViewById(R.id.p5);
        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Power-up 5!");
            }
        });

        return view;
    }

    private void catClicked() {
        //cat was clicked... do things
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
