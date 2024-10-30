package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddEventActivity extends AppCompatActivity {
    private EditText etEventName, etEventTime, etLocation, etDescription;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        etEventName = findViewById(R.id.etEventName);
        etEventTime = findViewById(R.id.etEventTime);
        etLocation = findViewById(R.id.etLocation);
        etDescription = findViewById(R.id.etDescription);
        Button btnSave = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("event")) {
            event = (Event) intent.getSerializableExtra("event");
            etEventName.setText(event.getName());
            etEventTime.setText(event.getTime());
            etLocation.setText(event.getLocation());
            etDescription.setText(event.getDescription());
        }

        btnSave.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("eventName", etEventName.getText().toString());
            resultIntent.putExtra("eventTime", etEventTime.getText().toString());
            resultIntent.putExtra("location", etLocation.getText().toString());
            resultIntent.putExtra("description", etDescription.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
