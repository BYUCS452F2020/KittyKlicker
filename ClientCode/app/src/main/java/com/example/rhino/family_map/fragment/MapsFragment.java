package com.example.rhino.family_map.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rhino.family_map.activity.PersonActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.rhino.family_map.R;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Map;
import java.util.Vector;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_BLUE;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_CYAN;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_GREEN;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_ROSE;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker;

public class MapsFragment extends Fragment {

    private GoogleMap map;
    private ImageView genderImage;
    private TextView personName;
    private TextView eventInfo;
    private Client client;

    private int lineWidth = 51;

    private static final int DEFAULT_LINE_WIDTH_CHANGE = 7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        setHasOptionsMenu(true);

        client = Client.getClient();

        genderImage = (ImageView) view.findViewById(R.id.mapfrag_image);
        genderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonActivity.class);
                startActivity(intent);
            }
        });
        personName = (TextView) view.findViewById(R.id.mapfrag_person_name);
        personName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonActivity.class);
                startActivity(intent);
            }
        });
        eventInfo = (TextView) view.findViewById(R.id.mapfrag_event_info);
        eventInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.map);
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        mapFragment.getMapAsync(new OnMapReadyCallback()
        {
            @Override
            public void onMapReady(GoogleMap googleMap)
            {
                map = googleMap;
                initMap();
            }
        });
    }

    public void initMap()
    {
        map.clear();
        if (!isIncluded(client.getSelectedEvent()))
        {
            client.setSelectedEvent(null);
            client.setSelectedPerson(null);
            updateSelectedEvent(null);
        }

        map.setMapType(client.getMapType());
        addMarkers();
        setMarkerListener();
        if (client.isZoomToSelectedEvent())
        {
            updateSelectedEvent(client.getSelectedZoomEvent());
            LatLng coordinate = new LatLng(Double.valueOf(client.getSelectedZoomEvent().getLatitude()), Double.valueOf(client.getSelectedZoomEvent().getLongitude()));
            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(coordinate, 5);
            map.animateCamera(location);
            client.setZoomToSelectedEvent(false);
        }
        else if (client.getSelectedEvent() != null)
        {
            updateSelectedEvent(client.getSelectedEvent());
        }
    }

    private void addMarkers()
    {
        for (Map.Entry<String, EventResponse> event : client.getAllEvents().entrySet()) {
            if (isIncluded(event.getValue()))
            {
                addMarker(event.getValue().getCity(), new LatLng(Double.valueOf(event.getValue().getLatitude()), Double.valueOf(event.getValue().getLongitude())), event.getValue().getEventID());
            }
        }
    }

    private void addMarker(String city, LatLng latLng, String eventID)
    {
        float color;
        switch (client.getAllEvents().get(eventID).getEventType().toLowerCase())
        {
            case "birth": color = HUE_GREEN; break;
            case "death": color = HUE_RED; break;
            case "marriage": color = HUE_ROSE; break;
            case "baptism": color = HUE_BLUE; break;
            default: color = HUE_CYAN;
        }

        MarkerOptions options =
                new MarkerOptions().position(latLng).title(city)
                        .icon(defaultMarker(color));
        Marker marker = map.addMarker(options);
        marker.setTag(eventID);
    }

    private void setMarkerListener() {
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                EventResponse event = client.getAllEvents().get((String)marker.getTag());
                client.setSelectedEvent(event);
                client.setSelectedPerson(client.getPersons().get(event.getPersonID()));
                updateSelectedEvent(event);
                return false;
            }
        });
    }

    private void updateSelectedEvent(EventResponse event)
    {
//        client.setSelectedEvent(event);
        if (event != null)
        {
            client.setSelectedPerson(client.getPersons().get(event.getPersonID()));
            PersonResponse person = client.getPersons().get(event.getPersonID());
            if (Character.valueOf(person.getGender()).equals('m'))
            {
                genderImage.setImageResource(R.drawable.ic_male);
            }
            else if (Character.valueOf(person.getGender()).equals('f'))
            {
                genderImage.setImageResource(R.drawable.ic_female);
            }
            personName.setText(client.getPersons().get(event.getPersonID()).getFirstName() + " " + client.getPersons().get(event.getPersonID()).getLastName());
            eventInfo.setText(event.getEventType()
                    + ": " + event.getCity()
                    + ", " + event.getCountry()
                    + " (" + event.getYear() + ")");
            drawLines(event);
        }
        else
        {
            genderImage.setImageResource(android.R.color.transparent);
            personName.setText("");
            eventInfo.setText("");
        }
    }

    private void drawLines(EventResponse event)
    {
        /*use Polyline line = map.addPolyline(new PolylineOptions()
            .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
            .width(5)
            .color(Color.RED));*/

        //remove all previous lines
        for (Polyline l : client.getLines())
        {
            l.remove();
        }

        //Spouse Line
        if (client.isSpouseSwitch())
        {
            // get earliest spouse event
            try
            {
                EventResponse spouseEvent = getEarliestEvent(client.getPersonsEvents().get(client.getPersons().get(event.getPersonID())
                        .getSpouse()));

                if (spouseEvent != null && isIncluded(spouseEvent))
                {
                    Polyline line = map.addPolyline(new PolylineOptions()
                            .add(new LatLng(Double.valueOf(event.getLatitude()), Double.valueOf(event.getLongitude())),
                                    new LatLng(Double.valueOf(spouseEvent.getLatitude()), Double.valueOf(spouseEvent.getLongitude())))
                            .width(5)
                            .color(Color.parseColor(client.getSpouseLinesColor())));
                    client.getLines().add(line);
                }
            }
            catch (Exception e)
            {
                Log.e("Drawing Spouse Lines", e.getMessage(), e);
            }
        }

        // Family Tree Lines
        if (client.isFamilySwitch())
        {
            lineWidth = 51;
            drawAncestralLines(event);
        }

        // Life Story Lines
        if (client.isLifeSwitch())
        {
            Vector<EventResponse> events = new Vector<>(client.getPersonsEvents().get(event.getPersonID()));

            Vector<EventResponse> filteredEvents = new Vector<>(events);
            for (EventResponse e : filteredEvents)
            {
                if (!isIncluded(e))
                {
                    events.remove(e);
                }
            }

            for (int i = 0; i < events.size() - 1; i++)
            {
                Polyline line = map.addPolyline(new PolylineOptions()
                        .add(new LatLng(Double.valueOf(events.get(i).getLatitude()), Double.valueOf(events.get(i).getLongitude())),
                                new LatLng(Double.valueOf(events.get(i + 1).getLatitude()), Double.valueOf(events.get(i + 1).getLongitude())))
                        .width(5)
                        .color(Color.parseColor(client.getLifeStoryLinesColor())));
                client.getLines().add(line);
            }
        }

    }

    private void drawAncestralLines(EventResponse event)
    {
        try
        {
            EventResponse father_event = getEarliestEvent(
                    client.getPersonsEvents()
                    .get(client.getPersons()
                    .get(event.getPersonID())
                    .getFather())
            );

            if (father_event != null)
            {
                Polyline line = map.addPolyline(new PolylineOptions()
                        .add(new LatLng(Double.valueOf(event.getLatitude()), Double.valueOf(event.getLongitude())),
                                new LatLng(Double.valueOf(father_event.getLatitude()), Double.valueOf(father_event.getLongitude())))
                        .width(lineWidth)
                        .color(Color.parseColor(client.getFamilyTreeLinesColor())));
                client.getLines().add(line);
            }

            EventResponse mother_event = getEarliestEvent(
                    client.getPersonsEvents()
                    .get(client.getPersons()
                    .get(event.getPersonID())
                    .getMother())
            );

            if (mother_event != null)
            {
                Polyline line = map.addPolyline(new PolylineOptions()
                        .add(new LatLng(Double.valueOf(event.getLatitude()), Double.valueOf(event.getLongitude())),
                                new LatLng(Double.valueOf(mother_event.getLatitude()), Double.valueOf(mother_event.getLongitude())))
                        .width(lineWidth)
                        .color(Color.parseColor(client.getFamilyTreeLinesColor())));
                client.getLines().add(line);
            }


            if (father_event != null)
            {
                lineWidth -= DEFAULT_LINE_WIDTH_CHANGE;
                drawAncestralLines(father_event);
            }

            if (mother_event != null)
            {
                lineWidth -= DEFAULT_LINE_WIDTH_CHANGE;
                drawAncestralLines(mother_event);
            }

            lineWidth += DEFAULT_LINE_WIDTH_CHANGE;
        }
        catch (Exception e)
        {
            Log.e("Drawing FamilyTreeLines", e.getMessage(), e);
        }
    }

    private EventResponse getEarliestEvent(Vector<EventResponse> events)
    {
        if (events == null)
        {
            return null;
        }
        EventResponse result = null;
        for (EventResponse e : events)
        {
            if (!isIncluded(e))
            {
                continue;
            }
            if (result == null)
            {
                result = e;
            }
            else if (result.getYear() > e.getYear())
            {
                result = e;
            }
        }
        return result;
    }

    private boolean isIncluded(EventResponse event)
    {
        if (event == null)
        {
            return false;
        }
        if (client.getFilters().get(event.getEventType().toLowerCase()) == null)
        {
            return true;
        }
        if (!client.getFilters().get("Father's Side"))
        {
            if (client.getOnFatherSide().get(event.getPersonID()) != null)
            {
                if (client.getOnFatherSide().get(event.getPersonID()))
                {
                    return false;
                }
            }
        }
        if (!client.getFilters().get("Mother's Side"))
        {
            if (client.getOnFatherSide().get(event.getPersonID()) != null)
            {
                if (!client.getOnFatherSide().get(event.getPersonID()))
                {
                    return false;
                }
            }
        }
        if (client.getPersons().get(event.getPersonID()).getGender() == 'm')
        {
            if (!client.getFilters().get("Male"))
            {
                return false;
            }
        }
        if (client.getPersons().get(event.getPersonID()).getGender() == 'f')
        {
            if (!client.getFilters().get("Female"))
            {
                return false;
            }
        }
        return client.getFilters().get(event.getEventType().toLowerCase());
    }
}
