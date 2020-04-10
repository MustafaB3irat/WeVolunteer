package com.example.volunteerdictionary.presenters;

import com.example.volunteerdictionary.interfaces.CountryFactory;
import com.example.volunteerdictionary.interfaces.Volunteers;
import com.example.volunteerdictionary.models.CountriesModel;
import com.example.volunteerdictionary.models.pojo.Country;
import com.example.volunteerdictionary.models.pojo.Volunteer;
import com.example.volunteerdictionary.models.VolunteersModel;

import java.util.List;

public class VolunteersPresenter implements Volunteers.VolunteersPresenter {

    private Volunteers.VolunteersModel volunteersModel = new VolunteersModel();
    private CountryFactory.CountryModel countryModel = new CountriesModel(this);

    private Volunteers.VolunteersView volunteersView;

    public VolunteersPresenter(Volunteers.VolunteersView volunteersView) {
        this.volunteersView = volunteersView;
    }

    @Override
    public List<Volunteer> getVolunteers() {
        return volunteersModel.getVolunteers();
    }

    @Override
    public boolean addVolunteer(String name, String phone, String country, String gender, String work, String communityWorkHours, String DOB, String isActive) {

        Volunteer volunteer = new Volunteer(name, country, phone, gender, DOB, communityWorkHours, work, isActive);
        return volunteersModel.AddVolunteer(volunteer);
    }

    @Override
    public void countriesList(List<Country> countries) {

        volunteersView.getCountries(countries);

    }

    @Override
    public void getCountries() {
        countryModel.getCountriesFromAPI();
    }
}
