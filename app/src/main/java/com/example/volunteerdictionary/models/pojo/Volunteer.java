package com.example.volunteerdictionary.models.pojo;
// plain data model in android sometimes it's also called POJO, so i created it  so that it holds data models ...
public class Volunteer {

    private String name, country, phone, gender, DOB, communityWorkHours, work, isActive;

    public Volunteer(String name, String country, String phone, String gender, String DOB, String communityWorkHours, String work, String isActive) {
        this.name = name;
        this.country = country;
        this.phone = phone;
        this.gender = gender;
        this.DOB = DOB;
        this.communityWorkHours = communityWorkHours;
        this.work = work;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public String getCommunityWorkHours() {
        return communityWorkHours;
    }

    public String getWork() {
        return work;
    }

    public String getIsActive() {
        return isActive;
    }
}
