package com.study.administration.administration.Controllers;

import com.study.administration.administration.Models.User;
import com.study.administration.administration.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController
{
    public final UserService userService;

    @GetMapping("/")
    public String administrationOfUsers(Model model)
    {
        var result = userService.FindAllUsers();
        model.addAttribute("users", result);
        return "index";
    }

    @GetMapping("/add")
    public String addUser()
    {
        return "adduser";
    }

    /*
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, Model model)
    {
        int statusCode = userService.AddUser(user);
        if (statusCode == 200)
        {
            return "redirect:/";
        }
        else
        {
            model.addAttribute("error", "Status code: 400 Bad Request");
            return "statusCode";
        }
    }
     */

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id, Model model)
    {
        int statusCode = userService.DeleteUserById(id);
        if (statusCode == 200)
        {
            return "redirect:/";
        }
        else
        {
            model.addAttribute("error", "Status code: 404 Not Found");
            return "statusCode";
        }

    }
    @GetMapping("/editview/{id}")
    public String editUserView(@PathVariable int id, Model model)
    {
        User user = userService.FindUserId(id);
        if(user == null)
        {
            model.addAttribute("error", "Status code: 404 Not Found");
            return "statusCode";
        }
        else
        {
            model.addAttribute("userPlaceHolder", user);
            return "editUser";
        }
    }
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable int id, String name, String surname, String email, String phoneNumber)
    {
        userService.EditUser(name, surname, email, phoneNumber, id);
        return "redirect:/";
    }

    @GetMapping ("/status/{id}")
    public String changeStatus(@PathVariable int id, Model model)
    {
        int statusCode = userService.ChangeStatus(id);
        if (statusCode == 200)
        {
            return "redirect:/";
        }
        else
        {
            model.addAttribute("error", "Status code: 404 Not Found");
            return "statusCode";
        }
    }
    @PostMapping("/find")
    public String findBy(String kind, String search, Model model)
    {
        List<User> result = userService.FindBy(kind, search);
        model.addAttribute("users", result);
        return "index";
    }
    @PostMapping("/date")
    public String dateSort(Date dateFrom, Date dateTo, Model model)
    {
        List<User> result = userService.TimeRange(dateFrom, dateTo);
        model.addAttribute("users", result);
        return "index";
    }
}
