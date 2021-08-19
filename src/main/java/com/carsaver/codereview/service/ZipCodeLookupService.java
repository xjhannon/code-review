package com.carsaver.codereview.service;

import org.springframework.stereotype.Service;

@Service
public class ZipCodeLookupService {

    // todo: comments here but not in other services
    // todo: zip code not a great lookup key for city. Note in PR then create issue.
    /**
     * @param zipCode
     * @return - returns city for the given zipCode.
     */
    public String lookupCityByZip(String zipCode) {
        // todo: adding a newline to this will make the output easier to read
        System.out.print("looking up city by zipCode (this might take a while)\n");

        try {
            //simulating a high latency call
            Thread.sleep(5000);
        } catch(Exception ignore) {
            // todo: could catch an InterrruptedException?
            // todo: swallowing exception here- best practice it to disclose this
        }

        // todo: was hardcoded return value of zip- can only be this value, and it's not a City, it's a zip code
        return "Some City";
    }

}
