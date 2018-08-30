package com.axmedbek.dao;

import com.axmedbek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

}
