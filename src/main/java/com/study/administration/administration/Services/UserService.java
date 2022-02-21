package com.study.administration.administration.Services;

import com.study.administration.administration.Models.User;
import com.study.administration.administration.Repositories.DummyUserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService
{
    private final DummyUserRepository repository;

    public List<User> FindAllUsers()
    {
        return repository.AllUsers();
    }

    public User FindUserId(int id)
    {
        return repository.FindById(id);
    }

    public int DeleteUserById(int id)
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

    public int ChangeStatus(int id)
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
    public void EditUser(String name, String surname, String email, String phoneNumber, int id)
    {
        User newDataUser = repository.FindById(id);
        newDataUser.setName(name);
        newDataUser.setSurname(surname);
        newDataUser.setEmail(email);
        newDataUser.setPhoneNumber(phoneNumber);
    }
    public List<User> FindBy(String kind, String value)
    {
        List<User>  allUsers = repository.AllUsers();

        if (Objects.equals(kind, "name"))
        {
             return allUsers.stream().filter(user -> Objects.equals(user.getName().toLowerCase(), value.toLowerCase()))
                                    .collect(Collectors.toList());
        }
        if (Objects.equals(kind, "surname"))
        {
            return allUsers.stream().filter(user -> Objects.equals(user.getSurname().toLowerCase(), value.toLowerCase()))
                                    .collect(Collectors.toList());
        }
        if (Objects.equals(kind, "email"))
        {
            return allUsers.stream().filter(user -> Objects.equals(user.getEmail().toLowerCase(), value.toLowerCase()))
                                    .collect(Collectors.toList());
        }
        if (Objects.equals(kind, "telephone"))
        {
            return allUsers.stream().filter(user -> Objects.equals(user.getPhoneNumber(), value)).collect(Collectors.toList());
        }
        else
        {
            var splited = value.toCharArray();
            for (int i = 0; i < value.length(); i++)
            {
                if (!Character.isDigit(splited[i]))
                {
                    return Collections.emptyList();
                }
            }
            int number = Integer.parseInt(value);
            return allUsers.stream().filter(user -> user.getId() == number).collect(Collectors.toList());
        }
    }
    @SneakyThrows
    public List<User> TimeRange(Date dateFrom, Date dateTo)
    {
        String dateFromInMili = dateFrom.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateFromInMili);
        long millisFrom = date.getTime();

        String dateToInMili = dateTo.toString();
        SimpleDateFormat sdfTO = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTO = sdf.parse(dateToInMili);
        long millisTo = dateTO.getTime();


        List<User>  allUsers = repository.AllUsers();
        return allUsers.stream().filter(user -> user.getDateOfCreationInLong() >= millisFrom && user.getDateOfCreationInLong() <= millisTo).collect(Collectors.toList());
    }
}
