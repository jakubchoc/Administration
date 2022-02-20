package com.study.administration.administration.Repositories;

import com.study.administration.administration.Models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DummyUserRepository
{
    private List<User> userData;

    public DummyUserRepository()
    {
        this.userData = new ArrayList<>();
        userData.add(new User(1,"Jakub", "Choc", true, "jakub.choc@gmail.com", "739230482", 956095200000L));
        userData.add(new User(2,"Karel", "Novák", true, "novak@gmail.com", "758628744", 906242400000L));
        userData.add(new User(3,"Jan", "Černý", true, "cerny@atlas.cz", "792587569", 1128722400000L));
        userData.add(new User(4,"Miroslav", "Novotný", false, "m.novotny@seznam.cz", "777555258", 1210888800000L));
        userData.add(new User(5,"Dominik", "Vávra", true, "vavra@seznam.cz", "776582888", 1138575600000L));
        userData.add(new User(6,"Emanuel", "Jarý", true, "jary.e@seznam.cz", "722526666", 1506722400000L));
        userData.add(new User(7,"Jaroslav", "Lohr", false, "lohr@seznam.cz", "777542098", 1602367200000L));
        userData.add(new User(8,"Petr", "Válek", false, "valek@centrum.cz", "722387657", 1458860400000L));
        userData.add(new User(9,"David", "Pálek", true, "palek@centrum.cz", "72|5984333", 1339365600000L));
    }
    public List<User> AllUsers()
    {
        return userData;
    }
    public User FindById(int id)
    {
        return userData.stream().filter(e -> e != null)
                                .filter(e -> e.getId() == id)
                                .findFirst()
                                .orElse(null);
    }
    public void DeleteById(int id)
    {
        var userForRemove = FindById(id);
        userData.remove(userForRemove);
    }
    /*
    public void AddUser(User user)
    {
        userData.add(user);
    }
     */


}
