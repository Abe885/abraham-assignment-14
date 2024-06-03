package com.coderscampus.assignment14.repository;

import com.coderscampus.assignment14.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final List<User> users = new CopyOnWriteArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public User save(User user) {
        user.setUserId(counter.incrementAndGet());
        users.add(user);
        return user;
    }

    public List<User> findAll() {
        return Collections.unmodifiableList(users);
    }

    public User findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }
}
