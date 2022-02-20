package com.study.administration.administration.Services;

import com.study.administration.administration.Models.User;
import com.study.administration.administration.Repositories.DummyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FilterService
{
    private final DummyUserRepository repository;

    public void SortingList(int onPage)
    {
        int numberOfPages = 0;
        var allUser = repository.AllUsers();
        var itemsInList = allUser.stream().count();
        if (itemsInList % onPage != 0)
        {
            numberOfPages += (itemsInList / onPage) +1;
        }
        else
        {
            numberOfPages += (itemsInList / onPage);
        }
        int indexCounter = 0;
        List<List<User>> ListOfList = new ArrayList<>();
        for (int i = 0; i < numberOfPages; i++)
        {
            for (int j = 0; j < onPage; j++)
            {
                List<User> innerList = new ArrayList<>();
                indexCounter++;
            }
        }
    }

}
