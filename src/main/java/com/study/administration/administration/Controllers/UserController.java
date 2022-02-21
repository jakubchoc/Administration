package com.study.administration.administration.Controllers;

import com.study.administration.administration.Models.User;
import com.study.administration.administration.Services.FilterService;
import com.study.administration.administration.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class UserController
{
    public final UserService userService;
    public final FilterService filterService;

    @GetMapping(value = {"/","/{pageNumber}"})
    public String index(@PathVariable (required = false)Integer pageNumber, Model model)
    {
        if (pageNumber == null)
        {
            pageNumber = 1;
        }
        int skip = (pageNumber-1) * 3;
        List<User> allUsers = userService.FindAllUsers();
        List<User> filterList = allUsers.stream().skip(skip).limit(3).collect(Collectors.toList());
        model.addAttribute("users", filterList);
        List<Integer> numbersForPaging = filterService.Paging(allUsers, 3);
        model.addAttribute("pages", numbersForPaging);
        model.addAttribute("path", "");
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
    /*
    @PostMapping("/find")
    public RedirectView findBy(String kind, String search)
    {
        return new RedirectView("/find/1");
    }

     */

    @PostMapping(value = {"/find","/find/{pageNumber}"})
    public String findBy(@PathVariable (required = false)Integer pageNumber, String kind, String search, Model model)
    {
        if (pageNumber == null)
        {
            pageNumber = 1;
        }
        int skip = (pageNumber-1) * 3;
        List<User> allUsers = userService.FindBy(kind, search);
        List<User> filterList = allUsers.stream().skip(skip).limit(3).collect(Collectors.toList());
        model.addAttribute("users", filterList);
        List<Integer> numbersForPaging = filterService.Paging(allUsers, 3);
        model.addAttribute("pages", numbersForPaging);
        model.addAttribute("path", "find");
        return "index";
    }

    @PostMapping(value = {"/date","/date/{pageNumber}"})
    public String dateSort(@PathVariable (required = false)Integer pageNumber, Date dateFrom, Date dateTo, Model model)
    {
        if (pageNumber == null)
        {
            pageNumber = 1;
        }
        int skip = (pageNumber-1) * 3;
        List<User> allUsers = userService.TimeRange(dateFrom, dateTo);
        List<User> filterList = allUsers.stream().skip(skip).limit(3).collect(Collectors.toList());
        model.addAttribute("users", filterList);
        List<Integer> numbersForPaging = filterService.Paging(allUsers, 3);
        model.addAttribute("pages", numbersForPaging);
        model.addAttribute("path","/date");
        return "index";
    }
    /*
    @PostMapping(value = {"/sort","/sort/{pageNumber}"})
    public String sort()

     */
}
