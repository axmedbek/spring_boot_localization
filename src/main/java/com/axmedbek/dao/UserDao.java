package com.axmedbek.dao;

import com.axmedbek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userDaoRepository")
public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
