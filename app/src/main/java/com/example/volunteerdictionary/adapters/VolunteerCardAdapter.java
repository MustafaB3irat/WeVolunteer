package com.example.volunteerdictionary.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volunteerdictionary.databinding.VolunteerCardBinding;
import com.example.volunteerdictionary.models.pojo.Volunteer;

import java.util.List;

public class VolunteerCardAdapter extends RecyclerView.Adapter<VolunteerCardAdapter.VolunteerCardViewHolder> {


    private List<Volunteer> volunteerList;

    public VolunteerCardAdapter(List<Volunteer> volunteerList) {
        this.volunteerList = volunteerList;
    }

    public class VolunteerCardViewHolder extends RecyclerView.ViewHolder {

        private VolunteerCardBinding volunteerCardBinding;

        public VolunteerCardViewHolder(@NonNull VolunteerCardBinding itemView) {
            super(itemView.getRoot());

            this.volunteerCardBinding = itemView;
        }
    }

    @NonNull
    @Override
    public VolunteerCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        VolunteerCardBinding volunteerCardBinding = VolunteerCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new VolunteerCardViewHolder(volunteerCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VolunteerCardViewHolder holder, int position) {

        Volunteer volunteer = volunteerList.get(position);

        holder.volunteerCardBinding.setVolunteer(volunteer);

    }

    @Override
    public int getItemCount() {
        return volunteerList.size();
    }
}
