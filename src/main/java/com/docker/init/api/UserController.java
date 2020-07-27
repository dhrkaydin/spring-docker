package com.docker.init.api;

import com.docker.init.exceptions.UserNotFoundException;
import com.docker.init.models.User;
import com.docker.init.repos.UserRepo;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepo users;

    public UserController(UserRepo users) {
        this.users = users;
    }

    @GetMapping
    public Iterable<User> getUsers() {
        return users.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return users.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @PostMapping
    public User addUser(User user) {
        return users.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User selectedUser = users.findById(id).orElseThrow(UserNotFoundException::new);

        selectedUser.setFirstName(user.getFirstName());
        selectedUser.setLastName(user.getLastName());
        selectedUser.setCountry(user.getCountry());

        return users.save(selectedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        users.findById(id).orElseThrow(UserNotFoundException::new);
        users.deleteById(id);
    }
}

