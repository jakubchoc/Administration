package com.study.administration.administration.Models;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Entity
public class User
{
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = false, nullable = false)
    private String name;

    @NotNull
    @Column(unique = false, nullable = false)
    private String surname;


    @NotNull
    @Column(unique = false, nullable = false)
    private Boolean status;

    @NotNull
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(unique = false, nullable = false)
    private Long dateOfCreation;


    public User(String name, String surname, boolean status, String email, String phoneNumber, Long dateOfCreation)
    {
        this.name = name;
        this.surname = surname;
        this.status = true;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfCreation = dateOfCreation;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDateOfCreation() {
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        Date result = new Date(dateOfCreation);
        return simple.format(result);

    }
    public void setDateOfCreation(Long dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}

