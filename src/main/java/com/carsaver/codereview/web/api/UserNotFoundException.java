package com.carsaver.codereview.web.api;

public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(Long id) {
        super("Could not find user: " + id);
    }
}
