package com.example.smsforlockdown;

public class UserDataModel {

    private String name,surname,address,id;

    public UserDataModel() {
    }

    public UserDataModel(String name, String surname, String address) {
        this.name = name;
        this.surname = surname;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
