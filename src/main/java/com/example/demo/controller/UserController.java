package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserDAO userDAO;

    @PostMapping()
    public void saveUser(@RequestBody User user) {
        User newUser = new User(user.getName());
        userDAO.save(newUser);
    }

    @GetMapping("{userId}")
    public User getUserById(@PathVariable(value = "userId") Integer userId) {
        User findUser = userDAO.findAll().
                stream().
                filter(user -> user.getId() == userId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User " + userId + " not exists"));
        return findUser;
    }
}
