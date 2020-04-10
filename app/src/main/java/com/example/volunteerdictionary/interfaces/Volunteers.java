package com.example.volunteerdictionary.interfaces;

import com.example.volunteerdictionary.models.pojo.Country;
import com.example.volunteerdictionary.models.pojo.Volunteer;

//this works as a factory for interfaces that handles all volunteers operations ....

import java.util.List;

public interface Volunteers {
    interface VolunteersModel {

        public List<Volunteer> getVolunteers();

        public boolean AddVolunteer(Volunteer volunteer);
    }

    interface VolunteersPresenter {

        public List<Volunteer> getVolunteers();

        public boolean addVolunteer(String name, String phone, String country, String gender, String work, String communityWorkHours, String DOB, String isActive);

        public void countriesList(List<Country> countries);
        public void getCountries();

    }


    interface VolunteersView {

        public void displayVolunteers();

        public void addVolunteerOnClickListener();

        public void setDatePicker();

        public boolean validate();

        public void getCountries(List<Country> countries);

        public void initOnCountrySelectedListener();

    }
}
