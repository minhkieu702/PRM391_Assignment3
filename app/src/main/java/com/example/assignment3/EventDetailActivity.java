package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EventDetailActivity extends AppCompatActivity {
    private TextView tvEventName, tvEventTime, tvLocation, tvDescription;
    private int eventPosition;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        tvEventName = findViewById(R.id.tvEventName);
        tvEventTime = findViewById(R.id.tvEventTime);
        tvLocation = findViewById(R.id.tvLocation);
        tvDescription = findViewById(R.id.tvDescription);
        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("event")) {
            event = (Event) intent.getSerializableExtra("event");
            eventPosition = intent.getIntExtra("position", -1);
            displayEventDetails(event);
        }

        btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(EventDetailActivity.this, AddEventActivity.class);
            editIntent.putExtra("event", event);
            startActivityForResult(editIntent, 2);
        });

        btnDelete.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("position", eventPosition);
            setResult(RESULT_FIRST_USER, resultIntent);
            finish();
        });
    }

    private void displayEventDetails(Event event) {
        if (event != null) {
            tvEventName.setText(event.getName());
            tvEventTime.setText(event.getTime());
            tvLocation.setText(event.getLocation());
            tvDescription.setText(event.getDescription());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            event.setName(data.getStringExtra("eventName"));
            event.setTime(data.getStringExtra("eventTime"));
            event.setLocation(data.getStringExtra("location"));
            event.setDescription(data.getStringExtra("description"));

            Intent resultIntent = new Intent();
            resultIntent.putExtra("event", event);
            resultIntent.putExtra("position", eventPosition);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}