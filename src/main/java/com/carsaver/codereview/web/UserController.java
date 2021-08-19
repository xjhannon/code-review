package com.carsaver.codereview.web;

import com.carsaver.codereview.model.User;
import com.carsaver.codereview.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// todo: should this be for web requests? Do this last!
// todo: whitelabel error page shown on the web for /users as an example
// todo: may want to choose one controller- the api controller vs having a duplicate
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets all users
     * @return User list
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    private List<User> findAll(){
        return userService.findAll();
//        return ResponseEntity.of(userService.findAll());
    }

    /**
     * Gets a user
     * @param id - assume valid existing id
     * @return User
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}")
    private ResponseEntity<User> findById(@PathVariable Long id){
        return ResponseEntity.of(userService.findById(id));
    }

}
