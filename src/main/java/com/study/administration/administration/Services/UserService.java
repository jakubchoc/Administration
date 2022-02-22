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
    public List<User> FindBy(String kind, String value, String status)
    {
        List<User>  allUsers = repository.AllUsers();
        boolean choice = true;
        if (status == "true")
        {
            choice = true;
        }
        if (status == "false")
        {
            choice = false;
        }

        if (Objects.equals(kind, "name"))
        {
            if (status == "all")
            {
                return findByNameAll(allUsers, value);
            }
            else
            {
                return findByName(allUsers, value, choice);
            }
        }
        if (Objects.equals(kind, "surname"))
        {
            if (status == "all")
            {
                return findBySurNameAll(allUsers, value);
            }
            else
            {
                return findBySurName(allUsers, value, choice);
            }
        }
        if (Objects.equals(kind, "email"))
        {
            if (status == "all")
            {
                return findByEmailAll(allUsers, value);
            }
            else
            {
                return findByEmail(allUsers, value, choice);
            }
        }
        if (Objects.equals(kind, "telephone"))
        {
            if (status == "all")
            {
                return findByTelephoneAll(allUsers, value);
            }
            else
            {
                return findByTelephone(allUsers, value, choice);
            }
        }
        else
        {
            if (status == "all")
            {
                return findByIdAll(allUsers, value);
            }
            else
            {
                return findById(allUsers, value, choice);
            }
        }
    }

    public List<User> findByName(List<User> allUsers, String value, boolean choice)
    {
        return allUsers.stream().filter(user -> user.getName().toLowerCase().contains(value.toLowerCase()) && user.getStatus() == choice)
                .collect(Collectors.toList());
    }

    public List<User> findBySurName(List<User> allUsers, String value, boolean choice)
    {
        return allUsers.stream().filter(user -> user.getSurname().toLowerCase().contains(value.toLowerCase()) && user.getStatus() == choice)
                .collect(Collectors.toList());
    }

    public List<User> findByTelephone(List<User> allUsers, String value, boolean choice)
    {
        return allUsers.stream().filter(user -> user.getPhoneNumber().toLowerCase().contains(value.toLowerCase()) && user.getStatus() == choice)
                .collect(Collectors.toList());
    }

    public List<User> findByEmail(List<User> allUsers, String value, boolean choice)
    {
        return allUsers.stream().filter(user -> user.getEmail().toLowerCase().contains(value.toLowerCase()) && user.getStatus() == choice)
                .collect(Collectors.toList());
    }

    public List<User> findById(List<User> allUsers, String value, boolean choice)
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
        return allUsers.stream().filter(user -> user.getId() == number  && user.getStatus() == choice).collect(Collectors.toList());
    }
    public List<User> findByNameAll(List<User> allUsers, String value)
    {
        return allUsers.stream().filter(user -> user.getName().toLowerCase().contains(value.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<User> findBySurNameAll(List<User> allUsers, String value)
    {
        return allUsers.stream().filter(user -> user.getSurname().toLowerCase().contains(value.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<User> findByTelephoneAll(List<User> allUsers, String value)
    {
        return allUsers.stream().filter(user -> user.getPhoneNumber().toLowerCase().contains(value.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<User> findByEmailAll(List<User> allUsers, String value)
    {
        return allUsers.stream().filter(user -> user.getEmail().toLowerCase().contains(value.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<User> findByIdAll(List<User> allUsers, String value)
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
