package com.jvcdp.repository;

import com.jvcdp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationUserRepository extends JpaRepository<AppUser, Long> {

    @Query("select count(e) from AppUser e")
    int countApplicationUsers();

    @Query("select count(e) FROM AppUser e where e.userName=:userName")
    int countApplicationUsersByUserName(@Param("userName")String userName);


    @Query("select e FROM AppUser e where e.userName=:userName")
    List<AppUser> getApplicationUsersByUserName(@Param("userName")String userName);


    @Query("select count(e) FROM AppUser e where e.email=:email")
    int countApplicationUsersByEmail(@Param("email")String email);


    @Query("select e FROM AppUser e where e.email=:email")
    List<AppUser> getApplicationUsersByEmail(@Param("email")String email);
}
