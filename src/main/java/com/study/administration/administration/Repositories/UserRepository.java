package com.study.administration.administration.Repositories;

import com.study.administration.administration.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{

}
