package com.example.volunteerdictionary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.example.volunteerdictionary.R;
import com.example.volunteerdictionary.models.pojo.Country;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {

    public CountryAdapter(Context context, List<Country> countries) {
        super(context, 0, countries);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.country_spinner_item, parent, false);

        AppCompatImageView flag = convertView.findViewById(R.id.flag);
        TextView name = convertView.findViewById(R.id.country_name);

        Country country = getItem(position);

        if (country != null) {
            Glide.with(getContext()).load(country.getFlag()).into(flag);

            name.setText(country.getName());
        }


        return convertView;
    }

}


