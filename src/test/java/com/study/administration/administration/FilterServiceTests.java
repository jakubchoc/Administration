package com.study.administration.administration;

import com.study.administration.administration.Models.User;
import com.study.administration.administration.Repositories.DummyUserRepository;
import com.study.administration.administration.Services.FilterService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterServiceTests {

    FilterService filterService = new FilterService(new DummyUserRepository());

        List<User> userData = List.of(
                new User(1, "Jakub", "Choc", true, "jakub.choc@gmail.com", "739230482", 956095200000L),
        new User(2, "Karel", "Novák", true, "novak@gmail.com", "758628744", 906242400000L),
        new User(3, "Jan", "Černý", true, "cerny@atlas.cz", "792587569", 1128722400000L),
        new User(8, "Petr", "Válek", false, "valek@centrum.cz", "722387657", 1458860400000L),
        new User(9, "David", "Pálek", true, "palek@centrum.cz", "72|5984333", 1339365600000L)
        );


    @Test
    public void PagingOk() {
        //Arrange
        List<Integer> expected = List.of(1,2,3);
        //Act
        var result = filterService.paging(userData, 2);
        //Assert
        assertEquals(result, expected);
    }
    public void PartOfListForPagingOk(){
        //Arrange
        List<User> expected = List.of(
                new User(1, "Jakub", "Choc", true, "jakub.choc@gmail.com", "739230482", 956095200000L),
                new User(2, "Karel", "Novák", true, "novak@gmail.com", "758628744", 906242400000L),
                new User(3, "Jan", "Černý", true, "cerny@atlas.cz", "792587569", 1128722400000L)
                );
        //Act
        var result = filterService.listingForPage(userData, 0);
        //Assert
        assertEquals(result, expected);
    }
    public void FindByOk(){
        //Arrange
        List<User> expected = List.of(
                new User(1, "Jakub", "Choc", true, "jakub.choc@gmail.com", "739230482", 956095200000L),
                new User(3, "Jan", "Černý", true, "cerny@atlas.cz", "792587569", 1128722400000L)
        );
        //Act
        var result = filterService.findBy("name", "j", "all");
        //Assert
        assertEquals(result, expected);
    }

}
