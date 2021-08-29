package com.example.demo.controller;

import com.example.demo.annotation.IsLibrarian;
import com.example.demo.annotation.IsReader;
import com.example.demo.annotation.IsReaderOrLibrarian;
import com.example.demo.model.User;
import com.example.demo.repository.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserDAO userDAO;

    @GetMapping("/save")
    @IsLibrarian
    public User saveUser(@RequestBody User user) {
        System.out.println(user);
        User newUser = new User(user.getName());
      return userDAO.save(newUser);
    }

    @GetMapping("/getAll")
    @IsReader
    List<User> getAllUsers(){
        return userDAO.findAll();
    }

    @GetMapping("{userId}")
    @IsLibrarian
    public User getUserById(@PathVariable(value = "userId") Integer userId) {
        return userDAO.findAll().
                      stream().
                      filter(user -> user.getId() == userId)
                      .findFirst()
                      .orElseThrow(() -> new IllegalArgumentException("User " + userId + " not exists"));
    }
}
