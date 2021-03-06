package com.study.administration.administration;

import com.study.administration.administration.Repositories.DummyUserRepository;
import com.study.administration.administration.Services.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTests {

    UserService userService = new UserService(new DummyUserRepository());

    @Test
    public void ChangeStatusTest200() {
        //Arrange
        int expected = 200;
        //Act
        var result = userService.ChangeStatus(1);
        //Assert
        assertEquals(result, expected);
    }
    public void ChangeStatusTest404() {
        //Arrange
        int expected = 404;
        //Act
        var result = userService.ChangeStatus(50);
        //Assert
        assertEquals(result, expected);
    }
    public void DeleteTest200() {
        //Arrange
        int expected = 200;
        //Act
        var result = userService.DeleteUserById(1);
        //Assert
        assertEquals(result, expected);
    }
    public void DeleteTest404() {
        //Arrange
        int expected = 404;
        //Act
        var result = userService.DeleteUserById(30);
        //Assert
        assertEquals(result, expected);
    }
    public void EditTest200() {
        //Arrange
        int expected = 200;
        //Act
        var result = userService.EditUser("Jan", "Vomacka",
                "jan.vomacka@gmail.com", "777777777", 1);
        //Assert
        assertEquals(result, expected);
    }
    public void EditTest404() {
        //Arrange
        int expected = 404;
        //Act
        var result = userService.EditUser("Jan", "Vomacka",
                "jan.vomacka@gmail.com", "777777777", 40);
        //Assert
        assertEquals(result, expected);
    }
}
