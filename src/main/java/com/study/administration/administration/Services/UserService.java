package com.study.administration.administration.Services;

import com.study.administration.administration.Models.User;
import com.study.administration.administration.Repositories.DummyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService {
    private final DummyUserRepository repository;

    public List<User> FindAllUsers() {
        return repository.AllUsers();
    }

    public User FindUserId(int id) {
        return repository.FindById(id);
    }

    public int DeleteUserById(int id) {
        var user = repository.FindById(id);
        if (user == null) {
            return 404;
        } else {
            repository.DeleteById(id);
            return 200;
        }
    }

    public int ChangeStatus(int id) {
        User user = repository.FindById(id);
        if (user == null) {
            return 404;
        } else {
            if (user.getStatus() == true) {
                user.setStatus(false);
            } else {
                user.setStatus(true);
            }
        }
        return 200;
    }

    public int EditUser(String name, String surname, String email, String phoneNumber, int id) {
        User user = repository.FindById(id);
        if (user == null)
        {
            return 404;
        }
        else{
            User newDataUser = repository.FindById(id);
            newDataUser.setName(name);
            newDataUser.setSurname(surname);
            newDataUser.setEmail(email);
            newDataUser.setPhoneNumber(phoneNumber);
            return 200;
        }
    }
}
