//package com.example.rhino.family_map.activity;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.rhino.family_map.R;
//import com.example.rhino.family_map.task.PersonAdapter;
//import com.example.rhino.family_map.task.EventAdapter;
//
//import java.util.Vector;
//
//public class PersonActivity extends AppCompatActivity {
//
//    private Client client;
//
//    private RecyclerView eventRecyclerView;
//    private RecyclerView personRecyclerView;
//
//    private EventAdapter eventAdapter;
//    private PersonAdapter personAdapter;
//
//    private ImageView eventDropdown;
//    private ImageView personDropdown;
//
//    private TextView firstname;
//    private TextView lastname;
//    private TextView gender;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_person);
//        client = Client.getClient();
//
//        PersonResponse selectedPerson = client.getSelectedPerson();
//        firstname = (TextView) findViewById(R.id.person_first);
//        firstname.setText(selectedPerson.getFirstName());
//
//        lastname = (TextView) findViewById(R.id.person_last);
//        lastname.setText(selectedPerson.getLastName());
//
//        eventDropdown = (ImageView) findViewById(R.id.person_life_events_dropdown);
//        eventDropdown.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                if (client.isLifeEventsVisible())
//                {
//                    eventRecyclerView.setVisibility(View.GONE);
//                    eventDropdown.setImageResource(R.drawable.ic_dropdown);
//                    client.setLifeEventsVisible(false);
//                }
//                else
//                {
//                    eventRecyclerView.setVisibility(View.VISIBLE);
//                    eventDropdown.setImageResource(R.drawable.ic_dropup);
//                    client.setLifeEventsVisible(true);
//                }
//            }
//        });
//
//        personDropdown = (ImageView) findViewById(R.id.person_family_dropdown);
//        personDropdown.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                if (client.isLifeEventsVisible())
//                {
//                    personRecyclerView.setVisibility(View.GONE);
//                    personDropdown.setImageResource(R.drawable.ic_dropdown);
//                    client.setLifeEventsVisible(false);
//                }
//                else
//                {
//                    personRecyclerView.setVisibility(View.VISIBLE);
//                    personDropdown.setImageResource(R.drawable.ic_dropup);
//                    client.setLifeEventsVisible(true);
//                }
//            }
//        });
//
//        gender = (TextView) findViewById(R.id.person_gender);
//        if (String.valueOf(selectedPerson.getGender()).equals("f"))
//        {
//            gender.setText("Female");
//        }
//        else if (String.valueOf(selectedPerson.getGender()).equals("m"))
//        {
//            gender.setText("Male");
//        }
//
//        eventRecyclerView = (RecyclerView) findViewById(R.id.person_events_recycler_view);
//        eventRecyclerView.setHasFixedSize(true);
//        eventRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        eventAdapter = new EventAdapter(getLifeEvents(), this);
//
//        eventRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        eventRecyclerView.setAdapter(eventAdapter);
//        eventAdapter.notifyDataSetChanged();
//
//
//        personRecyclerView = (RecyclerView) findViewById(R.id.person_family_recycler_view);
//        personRecyclerView.setHasFixedSize(true);
//        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        personAdapter = new PersonAdapter(getFamily(), this);
//
//        personRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        personRecyclerView.setAdapter(personAdapter);
//        personAdapter.notifyDataSetChanged();
//
//
//        if (!client.isLifeEventsVisible())
//        {
//            eventRecyclerView.setVisibility(View.GONE);
//            eventDropdown.setImageResource(R.drawable.ic_dropdown);
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home){
//            MainActivity.startTopActivity(this, false);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public Vector<EventResponse> getLifeEvents()
//    {
//        Vector<EventResponse> result = new Vector<>();
//        for (EventResponse e : client.getPersonsEvents().get(client.getSelectedPerson().getPersonID()))
//        {
//            if (!client.getFilters().get("Father's Side"))
//            {
//                if (client.getOnFatherSide().get(e.getPersonID()) != null)
//                {
//                    if (client.getOnFatherSide().get(e.getPersonID()))
//                    {
//                        continue;
//                    }
//                }
//            }
//            if (!client.getFilters().get("Mother's Side"))
//            {
//                if (client.getOnFatherSide().get(e.getPersonID()) != null)
//                {
//                    if (!client.getOnFatherSide().get(e.getPersonID()))
//                    {
//                        continue;
//                    }
//                }
//            }
//            if (client.getPersons().get(e.getPersonID()).getGender() == 'm')
//            {
//                if (!client.getFilters().get("Male"))
//                {
//                    continue;
//                }
//            }
//            if (client.getPersons().get(e.getPersonID()).getGender() == 'f')
//            {
//                if (!client.getFilters().get("Female"))
//                {
//                    continue;
//                }
//            }
//            if (!client.getFilters().get(e.getEventType().toLowerCase()))
//            {
//                continue;
//            }
//            result.add(e);
//        }
//        return result;
//    }
//
//    public Vector<PersonResponse> getFamily()
//    {
//        PersonResponse person = client.getSelectedPerson();
//        Vector<PersonResponse> result;
//        if (client.getChildren().get(person.getPersonID()) != null)
//        {
//            result = new Vector<>(client.getChildren().get(person.getPersonID()));
//        }
//        else
//        {
//            result = new Vector<>();
//        }
//        if (result == null)
//        {
//            result = new Vector<>();
//        }
//        if(person.getMother() != null && !person.getMother().isEmpty() && !person.getMother().equals("null"))
//        {
//            result.insertElementAt(client.getPersons().get(person.getMother()), 0);
//        }
//        if (person.getFather() != null && !person.getFather().isEmpty() && !person.getFather().equals("null"))
//        {
//            result.insertElementAt(client.getPersons().get(person.getFather()), 0);
//        }
//        if (person.getSpouse() != null && !person.getSpouse().isEmpty() && !person.getSpouse().equals("null"))
//        {
//            result.insertElementAt(client.getPersons().get(person.getSpouse()), 0);
//        }
//        return result;
//    }
//}
