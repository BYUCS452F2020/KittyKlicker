package com.example.rhino.family_map.task;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rhino.family_map.R;
import com.example.rhino.family_map.activity.EventActivity;
import com.example.rhino.family_map.activity.PersonActivity;
import com.example.rhino.family_map.activity.SearchActivity;

import java.util.Vector;

import model.client.Client;
import model.server.EventResponse;
import model.server.PersonResponse;

/**
 * Created by jswense2 on 12/11/18.
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE_EVENT = 0;
    private static final int ITEM_TYPE_PERSON = 1;

    private Vector<Object> items;
    private Context context;
    private Client client;

    private class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView name, details;
        public ImageView eventIcon;
        public String eventID;

        public EventViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.person_event_list_name);
            details = (TextView) view.findViewById(R.id.person_event_list_details);
            eventIcon = (ImageView) view.findViewById(R.id.person_event_list_image);
            name.setOnClickListener(this);
            details.setOnClickListener(this);
            eventIcon.setOnClickListener(this);
        }

        public void bind(EventResponse event)
        {
            name.setText(client.getPersons().get(event.getPersonID()).getFirstName() + " " + client.getPersons().get(event.getPersonID()).getLastName());
            details.setText(event.getEventType() + ": " + event.getCity() + ", " + event.getCountry() + " (" + event.getYear() + ")");
            eventIcon.setImageResource(R.drawable.ic_event_list);
            eventID = event.getEventID();
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, EventActivity.class);
            client.setSelectedZoomEvent(client.getAllEvents().get(eventID));
            client.setZoomToSelectedEvent(true);
            context.startActivity(intent);
        }
    }

    private class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView name, details;
        public ImageView gender;
        public String personID;

        public PersonViewHolder(View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.person_event_list_details);
            details = (TextView) view.findViewById(R.id.person_event_list_name);
            gender = (ImageView) view.findViewById(R.id.person_event_list_image);

            name.setOnClickListener(this);
            details.setOnClickListener(this);
            gender.setOnClickListener(this);
        }

        public void bind(PersonResponse person)
        {
            name.setText(person.getFirstName() + " " + person.getLastName());
            details.setText(getRelationshipToSelected(person.getPersonID()));
            if (person.getGender() == 'm')
            {
                gender.setImageResource(R.drawable.ic_male);
            }
            else
            {
                gender.setImageResource(R.drawable.ic_female);
            }
            personID = person.getPersonID();
        }

        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(context, PersonActivity.class);
            client.setSelectedPerson(client.getPersons().get(personID));
            context.startActivity(intent);
        }
    }

    public SearchAdapter(Vector<Object> items, Context c) {
        this.items = items;
        this.context = c;
        client = Client.getClient();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_row, parent, false);

        if (viewType == ITEM_TYPE_EVENT)
        {
            return new EventViewHolder(itemView);
        }
        else
        {
            return new PersonViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Object item = items.get(position);

        if (holder instanceof EventViewHolder)
        {
            ((EventViewHolder) holder).bind((EventResponse) item);
        }
        else
        {
            ((PersonViewHolder) holder).bind((PersonResponse) item);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        if (items.get(position) instanceof EventResponse)
        {
            return ITEM_TYPE_EVENT;
        }
        else
        {
            return ITEM_TYPE_PERSON;
        }
    }

    private String getRelationshipToSelected(String personID)
    {
        if (context instanceof SearchActivity)
        {
            return "";
        }
        PersonResponse selectedPerson = client.getSelectedPerson();
        if (personID.equals(selectedPerson.getFather()))
        {
            return "Father";
        }
        if (personID.equals(selectedPerson.getMother()))
        {
            return "Mother";
        }
        if (personID.equals(selectedPerson.getSpouse()))
        {
            return "Spouse";
        }
        else
        {
            return "Child";
        }
    }
}
