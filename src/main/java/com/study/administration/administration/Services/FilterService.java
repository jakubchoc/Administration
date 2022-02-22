package com.study.administration.administration.Services;

import com.study.administration.administration.Models.User;
import com.study.administration.administration.Repositories.DummyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilterService
{
    private final DummyUserRepository repository;

    public List<User> listingForPage(List<User> allUsers, int pageNumber)
    {
        int skip = (pageNumber-1) * 3;
        List<User> partOfList = allUsers.stream().skip(skip).limit(3).collect(Collectors.toList());
        return  partOfList;
    }
    public List<Integer> paging(List<User> usersForView, int onPage)
    {
        int numberOfPages = 0;
        var itemsInList = usersForView.stream().count();
        if (itemsInList % onPage != 0)
        {
            numberOfPages += (itemsInList / onPage) +1;
        }
        else
        {
            numberOfPages += (itemsInList / onPage);
        }

        int counter = 1;
        List<Integer> listOfIndexes = new ArrayList<>();
        for (int i = 0; i < numberOfPages; i++)
        {
            listOfIndexes.add(counter);
            counter++;
        }
        return listOfIndexes;
    }
}
