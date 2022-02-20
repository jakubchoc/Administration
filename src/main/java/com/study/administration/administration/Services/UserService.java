package com.study.administration.administration.Services;

import com.study.administration.administration.Models.User;
import com.study.administration.administration.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    public List<User> FindAllUsers()
    {
        return userRepository.findAll();
    }

    public User FindUserId(Long id)
    {
        return userRepository.findById(id).stream().findFirst().orElse(null);
    }
    public int DeleteUserById(Long id)
    {
        var user = userRepository.findById(id).stream().findFirst().orElse(null);
        if (user == null)
        {
            return 404;
        }
        else
        {
            userRepository.deleteById(id);
            return 200;
        }
    }

    public int AddUser(User user)
    {
        Date date = new Date();
        User newUser = new User(user.getName(), user.getSurname(), true, user.getEmail(), user.getPhoneNumber(), date.getTime()); // dodelat!!!

        if (newUser == null)
        {
            return 400;
        }
        else
        {
            userRepository.save(newUser);
            return 200;
        }
    }

    public int ChangeStatus(Long id)
    {
        User user = userRepository.findById(id).stream().findFirst().orElse(null);
        if (user == null)
        {
            return 404;
        }
        else
        {
            if (user.getStatus() == true)
            {
                user.setStatus(false);
                userRepository.save(user);
            }
            else
            {
                user.setStatus(true);
                userRepository.save(user);
            }
        }
        return 200;
    }
    public void EditUser(String name, String surname, String email, String phoneNumber, Long id)
    {
        User newDataUser = userRepository.findById(id).stream().findFirst().orElse(null);
        newDataUser.setName(name);
        newDataUser.setSurname(surname);
        newDataUser.setEmail(email);
        newDataUser.setPhoneNumber(phoneNumber);
        userRepository.save(newDataUser);
    }
}
