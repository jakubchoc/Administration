package com.study.administration.administration.Repositories;

import com.study.administration.administration.Models.User;
import java.util.ArrayList;
import java.util.List;

public class DummyUserRepository
{
    private List<User> userData;

    public DummyUserRepository()
    {
        this.userData = new ArrayList<>();
        userData.add(new User(1L,"Jakub", "Choc", true, "jakub.choc@gmail.com", "739230482", 956095200000L));
        userData.add(new User(2L,"Karel", "Novák", true, "novak@gmail.com", "758628744", 906242400000L));
        userData.add(new User(3L,"Jan", "Černý", true, "cerny@atlas.cz", "792587569", 1128722400000L));
        userData.add(new User(4L,"Miroslav", "Novotný", false, "m.novotny@seznam.cz", "777555258", 1210888800000L));
        userData.add(new User(5L,"Dominik", "Vávra", true, "vavra@seznam.cz", "776582888", 1138575600000L));
        userData.add(new User(6L,"Emanuel", "Jarý", true, "jary.e@seznam.cz", "722526666", 1506722400000L));
        userData.add(new User(7L,"Jaroslav", "Lohr", false, "lohr@seznam.cz", "777542098", 1602367200000L));
        userData.add(new User(8L,"Petr", "Válek", false, "valek@centrum.cz", "722387657", 1458860400000L));
        userData.add(new User(9L,"David", "Pálek", true, "palek@centrum.cz", "72|5984333", 1339365600000L));
    }
    public List<User> AllUsers()
    {
        return userData;
    }
    public User FindById(Long id)
    {
        return userData.stream().filter(e -> e != null)
                                .filter(e -> e.getId() == id)
                                .findFirst()
                                .orElse(null);
    }
    public void DeleteById(Long id)
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
