package com.example.rhino.family_map.task;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhino.family_map.R;
import com.example.rhino.family_map.activity.PersonActivity;
import com.example.rhino.family_map.activity.SearchActivity;

import java.util.Vector;

import model.client.Client;

/**
 * Created by jswense2 on 12/1/18.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
{

    private Vector<PersonResponse> familyList;
    private Client client;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView name, details;
        public ImageView gender;
        public String personID;

        public ViewHolder(View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.person_event_list_details);
            details = (TextView) view.findViewById(R.id.person_event_list_name);
            gender = (ImageView) view.findViewById(R.id.person_event_list_image);

            name.setOnClickListener(this);
            details.setOnClickListener(this);
            gender.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(context, PersonActivity.class);
            client.setSelectedPerson(client.getPersons().get(personID));
            context.startActivity(intent);
        }
    }


    public PersonAdapter(Vector<PersonResponse> family_List, Context c)
    {
        this.familyList = family_List;
        this.context = c;
        client = Client.getClient();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        PersonResponse person = familyList.get(position);
        holder.name.setText(person.getFirstName() + " " + person.getLastName());
        holder.details.setText(getRelationshipToSelected(person.getPersonID()));
        if (person.getGender() == 'm')
        {
            holder.gender.setImageResource(R.drawable.ic_male);
        }
        else
        {
            holder.gender.setImageResource(R.drawable.ic_female);
        }
        holder.personID = person.getPersonID();
    }

    @Override
    public int getItemCount() {
        return familyList.size();
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
