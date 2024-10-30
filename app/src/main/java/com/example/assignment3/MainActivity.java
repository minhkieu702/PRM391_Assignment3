package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private List<Event> eventList;
    private static final int REQUEST_ADD_EVENT = 1;
    private static final int REQUEST_EDIT_DELETE_EVENT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        Button btnAddEvent = findViewById(R.id.btnAddEvent);

        eventList = new ArrayList<>();
        adapter = new EventAdapter(eventList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Event event = eventList.get(position);
            Intent detailIntent = new Intent(MainActivity.this, EventDetailActivity.class);
            detailIntent.putExtra("event", event);
            detailIntent.putExtra("position", position);
            startActivityForResult(detailIntent, REQUEST_EDIT_DELETE_EVENT);
        });

        btnAddEvent.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
            startActivityForResult(intent, REQUEST_ADD_EVENT);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_EVENT && resultCode == RESULT_OK && data != null) {
            Event newEvent = new Event(
                    data.getStringExtra("eventName"),
                    data.getStringExtra("eventTime"),
                    data.getStringExtra("location"),
                    data.getStringExtra("description")
            );
            eventList.add(newEvent);
            adapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_EDIT_DELETE_EVENT) {
            int position = data.getIntExtra("position", -1);
            if (resultCode == RESULT_OK && data.hasExtra("event")) {
                Event updatedEvent = (Event) data.getSerializableExtra("event");
                eventList.set(position, updatedEvent);
                adapter.notifyDataSetChanged();
            } else if (resultCode == RESULT_FIRST_USER) {
                eventList.remove(position);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
