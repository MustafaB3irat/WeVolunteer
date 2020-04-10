package com.example.volunteerdictionary.models;
// main volunteers model that receives new entries and gives back list of volunteers from and to a presenter >>>>>> i'm using MVP (model - view - presenter) as a design pattern and it's commonly used

import com.example.volunteerdictionary.interfaces.Volunteers;
import com.example.volunteerdictionary.models.pojo.Volunteer;

import java.util.ArrayList;
import java.util.List;

public class VolunteersModel implements Volunteers.VolunteersModel {


   private  List<Volunteer> volunteers = new ArrayList<>();


    @Override
    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    @Override
    public boolean AddVolunteer(Volunteer volunteer) {

        volunteers.add(volunteer);
        return true;
    }
}
