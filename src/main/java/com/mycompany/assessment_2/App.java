/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.assessment_2;

import com.mycompany.assessment_2.dao.UserDao;
import com.mycompany.assessment_2.view.LoginJFrame;

/**
 *  id：21146528
 * @author Xuanhao Wang
 */
public class App {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        userDao.initTable();
        new LoginJFrame();
    }
}
