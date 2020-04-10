package com.example.volunteerdictionary.models.pojo;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("name")
    private String name;

    @SerializedName("flag")
    private String flag;


    public Country(String name, String flag) {
        this.name = name;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public String getFlag() {
        return flag;
    }
}
