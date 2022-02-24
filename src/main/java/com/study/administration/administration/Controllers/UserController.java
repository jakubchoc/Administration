package com.study.administration.administration.Controllers;

import com.study.administration.administration.Models.DateModelSession;
import com.study.administration.administration.Models.FindModelSession;
import com.study.administration.administration.Models.User;
import com.study.administration.administration.Services.FilterService;
import com.study.administration.administration.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;


@Controller
@AllArgsConstructor
public class UserController {
    public final UserService userService;
    public final FilterService filterService;

    @GetMapping(value = {"/", "/pageNumber={pageNumber}"})
    public String index(@PathVariable(required = false) Integer pageNumber, Model model) {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        List<User> allUsers = userService.FindAllUsers();
        List<Integer> numbersForPaging = filterService.paging(allUsers, 3);
        if (pageNumber > numbersForPaging.stream().count()) {
            model.addAttribute("error", "Status code: 400 Bad Request");
            return "statusCode";
        } else {
            model.addAttribute("users", filterService.listingForPage(allUsers, pageNumber));
            model.addAttribute("pages", numbersForPaging);
            model.addAttribute("path", "");
            return "index";
        }
    }

    @GetMapping("/delete/id={id}")
    public String deleteUser(@PathVariable int id, Model model) {
        int statusCode = userService.DeleteUserById(id);
        if (statusCode == 200) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "Status code: 404 Not Found");
            return "statusCode";
        }
    }

    @GetMapping("/editview/id={id}")
    public String editUserView(@PathVariable int id, Model model) {
        User user = userService.FindUserId(id);
        if (user == null) {
            model.addAttribute("error", "Status code: 404 Not Found");
            return "statusCode";
        } else {
            model.addAttribute("userPlaceHolder", user);
            return "editUser";
        }
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable int id, String name, String surname, String email, String phoneNumber) {
        userService.EditUser(name, surname, email, phoneNumber, id);
        return "redirect:/";
    }

    @GetMapping("/status/id={id}")
    public String changeStatus(@PathVariable int id, Model model) {
        int statusCode = userService.ChangeStatus(id);
        if (statusCode == 200) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "Status code: 404 Not Found");
            return "statusCode";
        }
    }

    @GetMapping(value = {"/find", "/find/pageNumber={pageNumber}"})
    public String findBy(@PathVariable(required = false) Integer pageNumber, String kind,
                         String search, String status, Model model, HttpServletRequest request) {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        HttpSession session = request.getSession();
        String kindInput = (String) session.getAttribute("kind");
        String searchInput = (String) session.getAttribute("search");
        String statusInput = (String) session.getAttribute("status");

        FindModelSession findModelSession = new FindModelSession();
        findModelSession.kindSession = kindInput;
        findModelSession.searchSession = searchInput;
        findModelSession.statusSession = statusInput;

        if (kind != null && search != null && status != null) {
            session.setAttribute("kind", kind);
            session.setAttribute("search", search);
            session.setAttribute("status", status);
            List<User> findedUsers = userService.FindBy(kind, search, status);
            List<Integer> numbersForPaging = filterService.paging(findedUsers, 3);
            if (pageNumber > numbersForPaging.stream().count()) {
                model.addAttribute("error", "Status code: 400 Bad Request");
                return "statusCode";
            } else {
                model.addAttribute("users", filterService.listingForPage(findedUsers, pageNumber));
                model.addAttribute("pages", numbersForPaging);
                model.addAttribute("path", "find");
                return "index";
            }
        } else {
            List<User> findedUsers = userService.FindBy(findModelSession.kindSession, findModelSession.searchSession,
                    findModelSession.statusSession);
            List<Integer> numbersForPaging = filterService.paging(findedUsers, 3);
            if (pageNumber > numbersForPaging.stream().count()) {
                model.addAttribute("error", "Status code: 400 Bad Request");
                return "statusCode";
            } else {
                model.addAttribute("users", filterService.listingForPage(findedUsers, pageNumber));
                model.addAttribute("pages", numbersForPaging);
                model.addAttribute("path", "find");
                return "index";
            }
        }
    }

    @GetMapping(value = {"/date", "/date/pageNumber={pageNumber}"})
    public String dateSort(@PathVariable(required = false) Integer pageNumber, Date dateFrom, Date dateTo, Model model, HttpServletRequest request) {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        HttpSession session = request.getSession();
        Date dateOne = (Date) session.getAttribute("dateOne");
        Date dateTwo = (Date) session.getAttribute("dateTwo");
        DateModelSession dateModelSession = new DateModelSession();
        dateModelSession.dateFromSession = dateOne;
        dateModelSession.dateToSession = dateTwo;

        if (dateFrom != null && dateTo != null) {
            session.setAttribute("dateOne", dateFrom);
            session.setAttribute("dateTwo", dateTo);
            List<User> usersInDate = userService.TimeRange(dateFrom, dateTo);
            List<Integer> numbersForPaging = filterService.paging(usersInDate, 3);
            if (pageNumber > numbersForPaging.stream().count()) {
                model.addAttribute("error", "Status code: 400 Bad Request");
                return "statusCode";
            } else {
                model.addAttribute("users", filterService.listingForPage(usersInDate, pageNumber));
                model.addAttribute("pages", numbersForPaging);
                model.addAttribute("path", "/date");
                return "index";
            }
        } else {
            List<User> usersInDate = userService.TimeRange(dateModelSession.dateFromSession, dateModelSession.dateToSession);
            List<Integer> numbersForPaging = filterService.paging(usersInDate, 3);
            if (pageNumber > numbersForPaging.stream().count()) {
                model.addAttribute("error", "Status code: 400 Bad Request");
                return "statusCode";
            } else {
                model.addAttribute("users", filterService.listingForPage(usersInDate, pageNumber));
                model.addAttribute("pages", numbersForPaging);
                model.addAttribute("path", "/date");
                return "index";
            }
        }
    }
}
