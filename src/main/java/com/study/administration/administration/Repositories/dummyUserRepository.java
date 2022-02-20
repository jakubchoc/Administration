package com.study.administration.administration.Repositories;

import com.study.administration.administration.Models.User;

import java.util.ArrayList;
import java.util.List;

public class dummyUserRepository
{
    private List<User> userData;

    public dummyUserRepository()
    {
        this.userData = new ArrayList<>();
        userData.add(new User("Jakub", "Choc", true, "jakub.choc@gmail.com", "739230482", 956095200000L));
        userData.add(new User("Karel", "Novák", true, "novak@gmail.com", "758628744", 906242400000L));
        userData.add(new User("Jan", "Černý", true, "cerny@atlas.cz", "792587569", 1128722400000L));
        userData.add(new User("Miroslav", "Novotný", true, "m.novotny@seznam.cz", "777555258", 1210888800000L));
        userData.add(new User("Dominik", "Vávra", true, "vavra@seznam.cz", "776582888", 1138575600000L));
        userData.add(new User("Emanuel", "Jarý", true, "jary.e@seznam.cz", "722526666", 1506722400000L));

    }
}
