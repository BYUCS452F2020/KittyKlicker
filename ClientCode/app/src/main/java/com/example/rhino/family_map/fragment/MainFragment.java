package com.example.rhino.family_map.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rhino.family_map.R;
import com.example.rhino.family_map.proxy.Proxy;

import com.example.rhino.family_map.activity.MainActivity;
import com.example.rhino.family_map.R;
import com.example.rhino.family_map.task.KlickTask;

import java.util.Map;
import java.util.Vector;

import model.client.KittyClient;
import model.request.KlickRequest;
import model.response.KlickResponse;

public class MainFragment extends Fragment implements KlickTask.KlickListener {

    private TextView username;
    private TextView teamName;
    private ImageView grumpyCat;
    private TextView score;
    private TextView kittyPower;
    private TextView powerups;
    private ImageView p1;
    private ImageView p2;
    private ImageView p3;
    private ImageView p4;
    private ImageView p5;
    private KittyClient client;
    private KlickRequest klickRequest;

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
        score.setText("Score: " + Integer.toString(client.getKittiesKlicked()));
        kittyPower = view.findViewById(R.id.kitty_power);
        kittyPower.setText("KittyPower: " + Integer.toString(client.getKittyPower()));
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
//        System.out.println("cat clicked");
        KlickTask klickTask = new KlickTask(this);
        klickTask.execute(new KlickRequest(client.getAuthToken()));
//        client.setKittiesKlicked(client.getKittiesKlicked()+client.getKittyPower());
//        Toast toast = Toast.makeText(getActivity(), "+1", Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.show();
//        score.setText("Score: " + Integer.toString(client.getKittiesKlicked()));
//        KlickResponse response = Proxy.Klick(new KlickRequest(client.getAuthToken()));
    }

    @Override
    public void onKlickComplete(KlickResponse response) {
//        System.out.println("klick complete");
        client.setKittiesKlicked(response.getKittiesKlicked());
        Toast toast = Toast.makeText(getActivity(), "+1", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        score.setText("Score: " + response.getKittiesKlicked());
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
