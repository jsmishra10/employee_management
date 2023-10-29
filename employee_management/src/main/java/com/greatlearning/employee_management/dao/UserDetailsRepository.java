package com.greatlearning.employee_management.dao;

import com.greatlearning.employee_management.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.name = :username")
    public User getUserByUsername(@Param("username") String username);

}
