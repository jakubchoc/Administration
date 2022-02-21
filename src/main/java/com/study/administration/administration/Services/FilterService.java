package com.study.administration.administration.Services;

import com.study.administration.administration.Models.User;
import com.study.administration.administration.Repositories.DummyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FilterService
{
    private final DummyUserRepository repository;

    public List<Integer> Paging(List<User> usersForView, int onPage)
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
