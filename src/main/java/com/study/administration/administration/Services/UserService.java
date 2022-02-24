package com.study.administration.administration.Services;

import com.study.administration.administration.Models.User;
import com.study.administration.administration.Repositories.DummyUserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public void EditUser(String name, String surname, String email, String phoneNumber, int id) {
        User newDataUser = repository.FindById(id);
        newDataUser.setName(name);
        newDataUser.setSurname(surname);
        newDataUser.setEmail(email);
        newDataUser.setPhoneNumber(phoneNumber);
    }

    public List<User> FindBy(String kind, String value, String status) {
        List<User> allUsers = repository.AllUsers();

        boolean choice = true;
        if (status.equals("false")) {
            choice = false;
        }
        boolean finalChoice = choice;

        Predicate<User> namePredicate = u -> u.getName().contains(value) && u.getStatus() == finalChoice;
        Predicate<User> surNamePredicate = u -> u.getSurname().contains(value) && u.getStatus() == finalChoice;
        Predicate<User> emailPredicate = u -> u.getEmail().contains(value) && u.getStatus() == finalChoice;
        Predicate<User> telephonePredicate = u -> u.getPhoneNumber().contains(value) && u.getStatus() == finalChoice;
        Predicate<User> idPredicate = u -> u.getId() == Integer.parseInt(value) && u.getStatus() == finalChoice;

        Predicate<User> namePredicateAll = u -> u.getName().contains(value);
        Predicate<User> surNamePredicateAll = u -> u.getSurname().contains(value);
        Predicate<User> emailPredicateAll = u -> u.getEmail().contains(value);
        Predicate<User> telephonePredicateAll = u -> u.getPhoneNumber().contains(value);
        Predicate<User> idPredicateAll = u -> u.getId() == Integer.parseInt(value);

        switch (kind) {
            case "name":
                if (status.equals("all")) {
                    return findAll(allUsers, namePredicateAll);
                }
                return find(allUsers, namePredicate, choice);
            case "surname":
                if (status.equals("all")) {
                    return findAll(allUsers, surNamePredicateAll);
                }
                return find(allUsers, surNamePredicate, choice);
            case "email":
                if (status.equals("all")) {
                    findAll(allUsers, emailPredicateAll);
                }
                return find(allUsers, emailPredicate, choice);
            case "telephone":
                if (status.equals("all")) {
                    findAll(allUsers, telephonePredicateAll);
                }
                return find(allUsers, telephonePredicate, choice);
            case "id":
                if (status.equals("all")) {
                    return findByIdAll(allUsers, value, idPredicateAll);
                }
        }
        return findById(allUsers, value, idPredicate, choice);
    }

    public List<User> findAll(List<User> allUsers, Predicate predicate) {
        return (List<User>) allUsers.stream().filter(predicate).collect(Collectors.toList());
    }

    public List<User> find(List<User> allUsers, Predicate predicate, boolean choice) {
        return (List<User>) allUsers.stream().filter(predicate).collect(Collectors.toList());
    }

    public List<User> findById(List<User> allUsers, String value, Predicate predicate, boolean choice) {
        if (value.matches("[0-9]+") == true) {
            return Collections.emptyList();
        }
        return (List<User>) allUsers.stream().filter(predicate).collect(Collectors.toList());
    }

    public List<User> findByIdAll(List<User> allUsers, String value, Predicate predicate) {
        if (value.matches("[0-9]+") == true) {
            return Collections.emptyList();
        }
        return (List<User>) allUsers.stream().filter(predicate).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<User> TimeRange(Date dateFrom, Date dateTo) {
        String dateFromInMili = dateFrom.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateFromInMili);
        long millisFrom = date.getTime();

        String dateToInMili = dateTo.toString();
        SimpleDateFormat sdfTO = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTO = sdf.parse(dateToInMili);
        long millisTo = dateTO.getTime();


        List<User> allUsers = repository.AllUsers();
        return allUsers.stream().filter(user -> user.getDateOfCreationInLong() >= millisFrom && user.getDateOfCreationInLong() <= millisTo).collect(Collectors.toList());
    }
}
