package com.study.administration.administration.Models;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class User
{
    private int id;

    private String name;

    private String surname;

    private Boolean status;

    private String email;

    private String phoneNumber;

    private Long dateOfCreation;


    public User(int id, String name, String surname, boolean status, String email, String phoneNumber, Long dateOfCreation)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.status = true;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfCreation = dateOfCreation;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getDateOfCreationInLong(){
        return dateOfCreation;
    }

    public String getDateOfCreation() {
        DateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        Date result = new Date(dateOfCreation);
        return simple.format(result);

    }
    public void setDateOfCreation(Long dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}

