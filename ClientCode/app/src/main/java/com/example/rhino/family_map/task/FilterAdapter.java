package com.example.rhino.family_map.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.rhino.family_map.R;

import java.util.Map;

/**
 * Created by jswense2 on 12/12/18.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {
    private Map.Entry<String, Boolean>[] filterList;
    private Context context;
    private Client client;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name, details;
        public Switch filterSwitch;
        public String filter;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.filter_title);
            details = view.findViewById(R.id.filter_description);
            filterSwitch = view.findViewById(R.id.filter_switch);
            name.setOnClickListener(this);
            details.setOnClickListener(this);
            filterSwitch.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (filterSwitch.isChecked())
            {
                client.getFilters().put(filter, true);
            }
            else
            {
                client.getFilters().put(filter, false);
            }
        }
    }


    public FilterAdapter(Map.Entry<String, Boolean>[] filterList, Context c) {
        this.filterList = filterList;
        this.context = c;
        client = Client.getClient();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filter_row_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map.Entry<String, Boolean> filter = filterList[position];

        if (filter.getKey().equals("Father's Side") || filter.getKey().equals("Mother's Side"))
        {
            holder.name.setText(filter.getKey());
        }
        else
        {
            holder.name.setText(filter.getKey() + " Events");
        }
        if (filter.getKey().equals("Father's Side") || filter.getKey().equals("Mother's Side"))
        {
            holder.details.setText("FILTER BY " + filter.getKey().toUpperCase() + " OF FAMILY");
        }
        else if (filter.getKey().equals("Male") || filter.getKey().equals("Female"))
        {
            holder.details.setText("FILTER EVENTS BASED ON GENDER");
        }
        else
        {
            holder.details.setText("FILTER BY " + filter.getKey().toUpperCase() + " EVENTS");
        }
        holder.filterSwitch.setChecked(client.getFilters().get(filter.getKey()));
        holder.filter = filter.getKey();
    }

    @Override
    public int getItemCount() {
        return filterList.length;
    }
}
