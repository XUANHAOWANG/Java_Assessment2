package com.mycompany.assessment_2.service;

import com.mycompany.assessment_2.dao.UserDao;
import com.mycompany.assessment_2.model.User;

import java.util.List;

/**
 * The service of user
 *  id：21146528
 * @author Xuanhao Wang
 */
public class UserService {
    private UserDao userDao = null;

    public UserService() {
        userDao = new UserDao();
    }

    public void initTable() {
        userDao.initTable();
    }

    /**
     * Check the username is valid
     * @param username
     * @return
     */
    public boolean isValidUsername(String username) {
        boolean result = false;
        username = username.trim();
        if (username.length() >= 4 && username.length() <= 13) {
            result = true;
        }
        return result;
    }

    /**
     * Check the password is valid
     * @param password
     * @return
     */
    public boolean isValidPassword(String password) {
        boolean result = false;
        password = password.trim();
        if (password.length() >= 4 && password.length() <= 13) {
            result = true;
        }
        return result;
    }

    /**
     * Check the age is valid
     * @param ageStr
     * @return
     */
    public boolean isValidAge(String ageStr) {
        boolean result = false;
        try {
            int age = Integer.parseInt(ageStr);
            if (age >= 0 && age <= 100) {
                result = true;
            }
        } catch (NumberFormatException e) {
        }

        return result;
    }

    /**
     * Check the email is valid
     * @param email
     * @return
     */
    public boolean isValidEmail(String email) {
        boolean result = false;
        if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            result = true;
        }
        return result;
    }

    /**
     * Add a user
     * @param user
     */
    public void addUser(User user) {
        userDao.addUser(user);
    }

    /**
     * Delete a user
     * @param id
     */
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    /**
     * Find users by username
     * @param username
     * @return
     */
    public List<User> findUsers(String username) {
        return userDao.findUsers(username);
    }
}
