package com.akash.employee_management_portal;

import com.akash.employee_management_portal.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testDefaultConstructor() {
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getType());
    }

    @Test
    void testParameterizedConstructor() {
        User parameterizedUser = new User("test@example.com", "Test", "User", "password123", "admin");
        assertEquals("test@example.com", parameterizedUser.getEmail());
        assertEquals("Test", parameterizedUser.getFirstName());
        assertEquals("User", parameterizedUser.getLastName());
        assertEquals("password123", parameterizedUser.getPassword());
        assertEquals("admin", parameterizedUser.getType());
    }

    @Test
    void testGetEmail() {
        user.setEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testSetEmail() {
        user.setEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testGetPassword() {
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testSetPassword() {
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testGetFirstName() {
        user.setFirstName("Test");
        assertEquals("Test", user.getFirstName());
    }

    @Test
    void testSetFirstName() {
        user.setFirstName("Test");
        assertEquals("Test", user.getFirstName());
    }

    @Test
    void testGetLastName() {
        user.setLastName("User");
        assertEquals("User", user.getLastName());
    }

    @Test
    void testSetLastName() {
        user.setLastName("User");
        assertEquals("User", user.getLastName());
    }

    @Test
    void testGetType() {
        user.setType("admin");
        assertEquals("admin", user.getType());
    }

    @Test
    void testSetType() {
        user.setType("admin");
        assertEquals("admin", user.getType());
    }
}
