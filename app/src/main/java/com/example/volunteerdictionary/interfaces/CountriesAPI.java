package com.example.volunteerdictionary.interfaces;

import com.example.volunteerdictionary.models.pojo.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesAPI {

    String URL = "https://restcountries.eu/rest/v2/";

    @GET("all")
    Call<List<Country>> getCountries();
}
