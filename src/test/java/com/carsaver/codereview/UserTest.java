package com.carsaver.codereview;

import com.carsaver.codereview.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    @Test
    public void testUser()
    {
        User user = new User();
        user.setFirstName("Foo");
        user.setLastName("Bar");
        user.setEmail("foo@bar.com");
        user.setCity("some city");
        user.setZipCode("55555");

        assertNotNull(user);
        assertEquals("Foo", user.getFirstName());
        assertEquals("Bar", user.getLastName());
        assertEquals("foo@bar.com", user.getEmail());
        assertEquals("some city", user.getCity());
        assertEquals("55555", user.getZipCode());
    }
}
