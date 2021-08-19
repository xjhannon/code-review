package com.carsaver.codereview.web.api;

import com.carsaver.codereview.model.User;
import com.carsaver.codereview.service.EmailService;
import com.carsaver.codereview.service.UserService;
import com.carsaver.codereview.service.ZipCodeLookupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// todo: could debate making calls with the DB record Id vs using a GUID value as an Id. May not want to expose record keys.
// todo: could have another unique field for this purpose containing a GUID thus hiding the real Id.
// todo: @Controller will require responses from each mapped call- use @RestController
// todo: add HTTP responses + logging to each method
// todo: was no logging in system
@RestController
public class UserApiController {

    public UserApiController(UserService userService, EmailService emailService, ZipCodeLookupService zipCodeLookupService) {
        this.userService = userService;
        this.emailService = emailService;
        this.zipCodeLookupService = zipCodeLookupService;
    }

    // todo: userrepo is an interface- java - Spring autowire interface - Stack Overflow --> https://stackoverflow.com/questions/31138830/spring-autowire-interface
    // todo: There is not a @qualifier on this- ok if not used elsewhere
    // todo: field injection not recommended?
    private final UserService userService;

    private final EmailService emailService;

    private final ZipCodeLookupService zipCodeLookupService;

    /**
     * Gets a list of users sorted ascending by id
     * @return List of users
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users?sortAsc=true")
    public List<User> findAllByOrderByIdAsc() {
        List users = userService.findAllByOrderByIdAsc();

        return users;
    }

    // todo: get used to create
    // todo: org.h2.jdbc.JdbcSQLSyntaxErrorException: Sequence "HIBERNATE_SEQUENCE" not found; SQL statement:
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    // todo: camelcase this one
    // todo: validate email
    // todo: allow to populate all fields in DB? Create some fields then call update to add remaining is two calls.
    public User createUser(@RequestBody User newUser) { // Unsupported Media Type
        String email = newUser.getEmail();

        // todo: interesting it disables a test account- might make sense
        if(!email.contains("@test.com")) {
            newUser.enabled = true;
        }

        User user = userService.save(newUser);

        // todo: style- would like to see a space in front of (
        // todo: why not send email to all new accounts?
        if(user.isEnabled()) {
            emailService.sendConfirmation(email);
        }

        return user;
    }

    /**
     * updates user's address
     * @param id - assume valid existing id
     * @return updated User
     */
    // todo: get used to do update
    // todo: here and others- send back a proper response: Spring guide: nonrest/src/main/java/payroll/EmployeeNotFoundException.java
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    // todo: update doesn't require a city?
    // todo: id as a Long here
    public User updateUserLocation(@PathVariable(value = "id") Long id, @RequestBody User newUser) {
        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        String zipCode = newUser.getZipCode();
        String city = newUser.getCity();
        user.setZipCode(zipCode);
        // todo: city call currently fixed response
        user.setCity(Optional.ofNullable(city).orElse(zipCodeLookupService.lookupCityByZip(zipCode)));

        return userService.save(user);
//        return ResponseEntity.ok(user);
    }

    // todo: get used for delete
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/users/{id}")
    // todo: should camel case the input
    // todo: verify object exists before delete- or catch exception?
    // todo: proper delete url does not have delete in it
    // todo: delete requires a Long input
    public void deleteUser(@PathVariable(value = "id") Long userId) {
        try {
            // todo: code smell extracting a Long from String?
            System.out.println("Deleting user Id: " + userId);
            userService.deleteById(userId);
            System.out.println("Deleted user Id: " + userId);
        }
        catch (Exception e) {
            System.out.println("Delete exception userId: " + userId + ": " + e.getMessage());
        }
    }
}
