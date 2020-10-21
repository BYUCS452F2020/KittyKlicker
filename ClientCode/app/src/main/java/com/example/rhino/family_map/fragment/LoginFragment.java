package com.example.rhino.family_map.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.rhino.family_map.activity.MainActivity;
import com.example.rhino.family_map.task.GetInfoTask;
import com.example.rhino.family_map.task.LoginTask;
import com.example.rhino.family_map.R;
import com.example.rhino.family_map.task.RegisterTask;

import java.net.URL;
import java.util.HashMap;
import java.util.Vector;

import model.LoginRequest;
import model.Login_RegisterResponse;
import model.RegisterRequest;
import model.client.Client;
import model.server.EventResponse;
import model.server.EventsResponse;
import model.server.Person;
import model.server.PersonResponse;
import model.server.PersonsResponse;

public class LoginFragment
        extends Fragment
        implements LoginTask.LoginListener,
        GetInfoTask.GetInfoListener,
        RegisterTask.RegisterListener
{

    private EditText serverHostField;
    private EditText serverPortField;
    private EditText usernameField;
    private EditText passwordField;
    private EditText firstnameField;
    private EditText lastnameField;
    private EditText emailField;

    private RadioButton maleRadio;
    private RadioButton femaleRadio;

    private Button login;
    private Button register;

    private LoginRequest loginRequest;
    private RegisterRequest registerRequest;

    private String host;
    private String port;
    private URL server;

    private Client client;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        loginRequest = new LoginRequest(null,null);
        registerRequest = new RegisterRequest(null,null,null,null,null,null);
        client = Client.getClient();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        client.clear();

        serverHostField = (EditText) v.findViewById(R.id.server_host);
        serverHostField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                client.setHost(s.toString());
                updateButtons();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // intentionally left blank
            }
        });

        serverPortField = (EditText) v.findViewById(R.id.server_port);
        serverPortField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                client.setPort(s.toString());
                updateButtons();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // intentionally left blank
            }
        });

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

        firstnameField = (EditText) v.findViewById(R.id.firstname);
        firstnameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerRequest.setFirstName(s.toString());
                updateButtons();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // intentionally left blank
            }
        });

        lastnameField = (EditText) v.findViewById(R.id.lastname);
        lastnameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerRequest.setLastName(s.toString());
                updateButtons();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // intentionally left blank
            }
        });

        emailField = (EditText) v.findViewById(R.id.email);
        emailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerRequest.setEmail(s.toString());
                updateButtons();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // intentionally left blank
            }
        });

        maleRadio = (RadioButton) v.findViewById(R.id.maleRadio);
        maleRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerRequest.setGender("m");
                updateButtons();
            }
        });

        femaleRadio = (RadioButton) v.findViewById(R.id.femaleRadio);
        femaleRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerRequest.setGender("f");
                updateButtons();
            }
        });

        login = (Button) v.findViewById(R.id.login_button);
        login.setEnabled(false);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.setLoginRequest(loginRequest);
                loginClicked();
            }
        });

        register = (Button) v.findViewById(R.id.register_button);
        register.setEnabled(false);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.setRegisterRequest(registerRequest);
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
            client.setAuth(response.getAuthToken());
            client.setPersonID(response.getPersonID());
            GetInfoTask getInfoTask = new GetInfoTask(this);
            getInfoTask.execute(response.getAuthToken());
        }
    }

    public void onRegisterComplete(Login_RegisterResponse response)
    {
        onLoginComplete(response);
    }

    public void onGetInfoComplete(Pair<PersonsResponse, EventsResponse> info)
    {
        if (info.first.getMessage() != null)
        {
            Toast.makeText(getActivity(), info.first.getMessage(), Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (info.second.getMessage() != null)
            {
                Toast.makeText(getActivity(), info.second.getMessage(), Toast.LENGTH_SHORT).show();
            }
            else
            {
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

                Toast.makeText(getActivity(),
                        "SUCCESS welcome, "
                        + client.getPersons().get(client.getPersonID()).getFirstName()
                        + " "
                        + client.getPersons().get(client.getPersonID()).getLastName(),
                        Toast.LENGTH_LONG).show();
                ((MainActivity)getActivity()).onLoginComplete();
            }
        }

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
        if (client.getHost() == null || client.getHost().isEmpty())
            client.setHost("10.0.2.2");
        if (client.getPort() == null || client.getPort().isEmpty())
            client.setPort("8080");
        if (loginRequest.getUserName() == null || loginRequest.getUserName().isEmpty())
            loginRequest.setUserName("sheila");
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty())
            loginRequest.setPassword("parker");
        return true;
    }

    private boolean updateRegister()
    {
        if (registerRequest.getFirstName() == null || registerRequest.getFirstName().isEmpty())
            return false;
        if (registerRequest.getLastName() == null || registerRequest.getLastName().isEmpty())
            return false;
        if (registerRequest.getEmail() == null || registerRequest.getEmail().isEmpty())
            return false;
        if (registerRequest.getGender() == null || registerRequest.getGender().isEmpty())
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
