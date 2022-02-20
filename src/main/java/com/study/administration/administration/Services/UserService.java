package com.study.administration.administration.Services;

import com.study.administration.administration.Models.User;
import com.study.administration.administration.Repositories.DummyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService
{
    private final DummyUserRepository repository;

    public List<User> FindAllUsers()
    {
        return repository.AllUsers();
    }

    public User FindUserId(Long id)
    {
        return repository.FindById(id);
    }

    public int DeleteUserById(Long id)
    {
        var user = repository.FindById(id);
        if (user == null)
        {
            return 404;
        }
        else
        {
            repository.DeleteById(id);
            return 200;
        }
    }

    /*
    public int AddUser(User user)
    {
        Date date = new Date();
        User newUser = new User(user.getName(), user.getSurname(), user.getEmail(), user.getPhoneNumber(), date.getTime()); // dodelat!!!

        if (user == null)
        {
            return 400;
        }
        else
        {
            repository.AddUser(newUser);
            return 200;
        }
    }
     */

    public int ChangeStatus(Long id)
    {
        User user = repository.FindById(id);
        if (user == null)
        {
            return 404;
        }
        else
        {
            if (user.getStatus() == true)
            {
                user.setStatus(false);
            }
            else
            {
                user.setStatus(true);
            }
        }
        return 200;
    }
    public void EditUser(String name, String surname, String email, String phoneNumber, Long id)
    {
        User newDataUser = repository.FindById(id);
        newDataUser.setName(name);
        newDataUser.setSurname(surname);
        newDataUser.setEmail(email);
        newDataUser.setPhoneNumber(phoneNumber);
    }
}
