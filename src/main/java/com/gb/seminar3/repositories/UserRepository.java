package com.gb.seminar3.repositories;

import com.gb.seminar3.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    void deleteById(Long id);

    @Transactional
    @Query(value = "select id, name, age from users where id = :id", nativeQuery = true)
    User findUserById(Long id);

    @Transactional
    @Modifying
    @Query(value = "update users set name = :name, age = :age where id = :id", nativeQuery = true)
    void edit(String name, Integer age, Long id);

}
