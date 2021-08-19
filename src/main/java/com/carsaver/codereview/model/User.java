package com.carsaver.codereview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    // todo: The class must have a public or protected, no-argument constructor
    @Id
    // todo: GenerationType.AUTO was looking for a hibernate_sequence.
    // todo: since using liquibase, this does not get created. GenerationType.IDENTITY works.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    // todo: not private or public- was it to be a persisted instance var?
    // todo: more explicit to init to false, and don't need to init it where created.
    public Boolean enabled = false;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    // TODO zip declared after set/get- move to be with other declarations
    private String zipCode;

    public Long getId() {
        return id;
    }

    // TODO should probably not be exposed? Might be ok on a lookup?
    public void setId(Long id) {
        this.id=id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

        public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
