package com.mycompany.assessment_2.service;

import com.mycompany.assessment_2.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService = null;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    @Test
    void isValidUsername() {
        assertFalse(userService.isValidUsername(""));
        assertFalse(userService.isValidUsername("abc"));
        assertTrue(userService.isValidUsername("William"));
        assertTrue(userService.isValidUsername("William Tung"));
        assertFalse(userService.isValidUsername("Alexander Nathaniel Maximilian"));
    }

    @Test
    void isValidPassword() {
        assertFalse(userService.isValidPassword(""));
        assertFalse(userService.isValidPassword("abc"));
        assertTrue(userService.isValidPassword("William"));
        assertTrue(userService.isValidPassword("William Tung"));
        assertFalse(userService.isValidPassword("Alexander Nathaniel Maximilian"));
    }


    @Test
    void isValidAge() {
        assertFalse(userService.isValidAge(""));
        assertFalse(userService.isValidAge("abc"));
        assertTrue(userService.isValidAge("18"));
        assertTrue(userService.isValidAge("32"));
        assertFalse(userService.isValidAge("18a"));
    }

    @Test
    void isValidEmail() {
        assertFalse(userService.isValidEmail(""));
        assertFalse(userService.isValidEmail("abc"));
        assertTrue(userService.isValidEmail("william@gmail.com"));
        assertTrue(userService.isValidEmail("will134@yahoo.com"));
        assertFalse(userService.isValidEmail("will@#123"));
    }

    @Test
    void initTable() {
        userService.initTable();
        List<User> users=userService.findUsers(null);
        assertEquals(2,users.size());
    }
}