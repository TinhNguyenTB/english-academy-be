package com.englishacademy.repository;

import com.englishacademy.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    Page<User> findByEmailContainingIgnoreCase(String email, Pageable pageable);
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<User> findByNameContainingIgnoreCaseAndEmailContainingIgnoreCase(String name, String email, Pageable pageable);
}
