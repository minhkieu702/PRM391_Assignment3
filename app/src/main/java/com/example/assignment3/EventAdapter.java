package com.example.assignment3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> eventList;
    private OnItemClickListener listener;

    // Interface to handle item clicks
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Constructor for EventAdapter
    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event currentEvent = eventList.get(position);
        holder.tvEventName.setText(currentEvent.getName());
        holder.tvEventTime.setText(currentEvent.getTime());
        holder.tvLocation.setText(currentEvent.getLocation());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    // ViewHolder class for the adapter
    public static class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView tvEventName, tvEventTime, tvLocation;

        public EventViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            tvEventName = itemView.findViewById(R.id.tvEventName);
            tvEventTime = itemView.findViewById(R.id.tvEventTime);
            tvLocation = itemView.findViewById(R.id.tvLocation);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}

