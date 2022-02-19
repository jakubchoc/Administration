package com.study.administration.administration.Models;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class User
{
    @Id
    @GeneratedValue
    private int ID;

    @NotNull
    @Column(unique = false, nullable = false)
    private String Name;

    @NotNull
    @Column(unique = false, nullable = false)
    private String Surname;


    @NotNull
    @Column(unique = false, nullable = false)
    private Boolean Status;

    @NotNull
    @Column(unique = true, nullable = false)
    private String Email;

    @NotNull
    @Column(unique = true, nullable = false)
    private String PhoneNumber;

    @NotNull
    @Column(unique = false, nullable = false)
    private Date DateOfCreation;

    public User(String name, String surname, String email, String phoneNumber, Date dateOfCreation) {
        Name = name;
        Surname = surname;
        Status = true;
        Email = email;
        PhoneNumber = phoneNumber;
        DateOfCreation = dateOfCreation;
    }

    public User() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Date getDateOfCreation() {
        return DateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        DateOfCreation = dateOfCreation;
    }
}
