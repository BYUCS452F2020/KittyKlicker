package com.example.rhino.family_map.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rhino.family_map.activity.MainActivity;
import com.example.rhino.family_map.task.LoginTask;
import com.example.rhino.family_map.R;
import com.example.rhino.family_map.task.RegisterTask;

import model.client.KittyClient;
import model.request.LoginRequest;
import model.request.RegisterRequest;
import model.response.Login_RegisterResponse;

public class LoginFragment
        extends Fragment
        implements LoginTask.LoginListener,
        RegisterTask.RegisterListener
{

    private EditText usernameField;
    private EditText passwordField;
    private EditText teamField;

    private Button login;
    private Button register;

    private LoginRequest loginRequest;
    private RegisterRequest registerRequest;

    private KittyClient client;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        loginRequest = new LoginRequest(null,null);
        registerRequest = new RegisterRequest(null,null,null);
        client = KittyClient.getClient();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        client.clear();

        usernameField = (EditText) v.findViewById(R.id.username);
        usernameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginRequest.setUserName(s.toString());
                registerRequest.setUserName(s.toString());
                updateButtons();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // intentionally left blank
            }
        });

        passwordField = (EditText) v.findViewById(R.id.password);
        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginRequest.setPassword(s.toString());
                registerRequest.setPassword(s.toString());
                updateButtons();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // intentionally left blank
            }
        });

        teamField = (EditText) v.findViewById(R.id.team);
        teamField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerRequest.setTeamName(s.toString());
                updateButtons();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // intentionally left blank
            }
        });

        login = (Button) v.findViewById(R.id.login_button);
        login.setEnabled(false);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClicked();
            }
        });

        register = (Button) v.findViewById(R.id.register_button);
        register.setEnabled(false);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerClicked();
            }
        });

        updateButtons();
        return v;
    }

    public void onLoginComplete(Login_RegisterResponse response)
    {
        if (response.getMessage() != null)
        {
            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
        }
        else
        {
            client.setAuthToken(response.getAuthToken());
            client.setUserID(response.getUserID());
            client.setUserID(response.getUserID());
            client.setKittiesKlicked(response.getKittiesKlicked());
            client.setKittyPower(response.getKittyPower());
            client.setPowerups(response.getPowerups());
            client.setTeamName(response.getTeamName());
            ((MainActivity) getActivity()).onLoginComplete();
        }
    }

    public void onRegisterComplete(Login_RegisterResponse response)
    {
        onLoginComplete(response);
    }

    private void loginClicked()
    {
        LoginTask loginTask = new LoginTask(this);
        loginTask.execute(loginRequest);
    }

    private void registerClicked()
    {
        RegisterTask registerTask = new RegisterTask(this);
        registerTask.execute(registerRequest);
    }

    private boolean updateLogin()
    {
        if (loginRequest.getUserName() == null || loginRequest.getUserName().isEmpty())
            loginRequest.setUserName("sheila");
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty())
            loginRequest.setPassword("parker");
        return true;
    }

    private boolean updateRegister()
    {
        if (registerRequest.getTeamName() == null || registerRequest.getTeamName().isEmpty())
            return false;
        return true;
    }

    private void updateButtons()
    {
        if (updateLogin())
        {
            login.setEnabled(true);
            if (updateRegister())
            {
                register.setEnabled(true);
            }
            else
            {
                register.setEnabled(false);
            }
        }
        else
        {
            login.setEnabled(false);
            register.setEnabled(false);
        }
    }

}
