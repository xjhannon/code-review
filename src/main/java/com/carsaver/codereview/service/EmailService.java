package com.carsaver.codereview.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendConfirmation(String email) {
        // todo: missing newline to make easier to read output
        System.out.print("sending confirmation to " + email + "\n");
    }

}
