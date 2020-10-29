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
import com.example.rhino.family_map.activity.EventActivity;

import java.util.Vector;

/**
 * Created by jswense2 on 12/1/18.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private Vector<EventResponse> eventsList;
    private Context context;
    private Client client;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name, details;
        public ImageView eventIcon;
        public String eventID;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.person_event_list_name);
            details = (TextView) view.findViewById(R.id.person_event_list_details);
            eventIcon = (ImageView) view.findViewById(R.id.person_event_list_image);
            name.setOnClickListener(this);
            details.setOnClickListener(this);
            eventIcon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, EventActivity.class);
            client.setSelectedZoomEvent(client.getAllEvents().get(eventID));
            client.setZoomToSelectedEvent(true);
            context.startActivity(intent);
        }
    }


    public EventAdapter(Vector<EventResponse> events_List, Context c) {
        this.eventsList = events_List;
        this.context = c;
        client = Client.getClient();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        EventResponse event = eventsList.get(position);
        holder.name.setText(client.getPersons().get(event.getPersonID()).getFirstName() + " " + client.getPersons().get(event.getPersonID()).getLastName());
        holder.details.setText(event.getEventType() + ": " + event.getCity() + ", " + event.getCountry() + " (" + event.getYear() + ")");
        holder.eventIcon.setImageResource(R.drawable.ic_event_list);
        holder.eventID = event.getEventID();
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
