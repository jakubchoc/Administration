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
public class FilterService {
    private final DummyUserRepository repository;

    public List<User> listingForPage(List<User> allUsers, int pageNumber) {
        int skip = (pageNumber - 1) * 3;
        List<User> partOfList = allUsers.stream().skip(skip).limit(3).collect(Collectors.toList());
        return partOfList;
    }

    public List<Integer> paging(List<User> usersForView, int onPage) {
        int numberOfPages = 0;
        var itemsInList = usersForView.stream().count();
        if (itemsInList % onPage != 0) {
            numberOfPages += (itemsInList / onPage) + 1;
        } else {
            numberOfPages += (itemsInList / onPage);
        }

        int counter = 1;
        List<Integer> listOfIndexes = new ArrayList<>();
        for (int i = 0; i < numberOfPages; i++) {
            listOfIndexes.add(counter);
            counter++;
        }
        return listOfIndexes;
    }
    public List<User> findBy(String kind, String value, String status) {
        List<User> allUsers = repository.AllUsers();

        boolean choice = true;
        if (status.equals("false")) {
            choice = false;
        }
        boolean finalChoice = choice;

        Predicate<User> namePredicate = u -> u.getName().toLowerCase().contains(value.toLowerCase()) && u.getStatus() == finalChoice;
        Predicate<User> surNamePredicate = u -> u.getSurname().toLowerCase().contains(value.toLowerCase()) && u.getStatus() == finalChoice;
        Predicate<User> emailPredicate = u -> u.getEmail().toLowerCase().contains(value.toLowerCase()) && u.getStatus() == finalChoice;
        Predicate<User> telephonePredicate = u -> u.getPhoneNumber().toLowerCase().contains(value.toLowerCase()) && u.getStatus() == finalChoice;
        Predicate<User> idPredicate = u -> u.getId() == Integer.parseInt(value) && u.getStatus() == finalChoice;

        Predicate<User> namePredicateAll = u -> u.getName().toLowerCase().contains(value.toLowerCase());
        Predicate<User> surNamePredicateAll = u -> u.getSurname().toLowerCase().contains(value.toLowerCase());
        Predicate<User> emailPredicateAll = u -> u.getEmail().toLowerCase().contains(value.toLowerCase());
        Predicate<User> telephonePredicateAll = u -> u.getPhoneNumber().toLowerCase().contains(value.toLowerCase());
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
        return allUsers.stream().filter(predicate::test).collect(Collectors.toList());
    }

    public List<User> find(List<User> allUsers, Predicate predicate, boolean choice) {
        return allUsers.stream().filter(predicate::test).collect(Collectors.toList());
    }

    public List<User> findById(List<User> allUsers, String value, Predicate predicate, boolean choice) {
        if (!value.matches("[0-9]+") == true) {
            return Collections.emptyList();
        }
        return allUsers.stream().filter(predicate::test).collect(Collectors.toList());
    }

    public List<User> findByIdAll(List<User> allUsers, String value, Predicate predicate) {
        if (!value.matches("[0-9]+") == true) {
            return Collections.emptyList();
        }
        return allUsers.stream().filter(predicate::test).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<User> timeRange(Date dateFrom, Date dateTo) {
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
