package com.example.volunteerdictionary.models;

import com.example.volunteerdictionary.interfaces.CountriesAPI;
import com.example.volunteerdictionary.interfaces.CountryFactory;
import com.example.volunteerdictionary.interfaces.Volunteers;
import com.example.volunteerdictionary.models.pojo.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesModel implements CountryFactory.CountryModel {

    private Volunteers.VolunteersPresenter volunteersPresenter;

    public CountriesModel(Volunteers.VolunteersPresenter volunteersPresenter) {
        this.volunteersPresenter = volunteersPresenter;
    }

    @Override
    public void getCountriesFromAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CountriesAPI.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CountriesAPI countriesAPI = retrofit.create(CountriesAPI.class);

        Call<List<Country>> call = countriesAPI.getCountries();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful())
                    volunteersPresenter.countriesList(response.body());


            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });
    }
}
