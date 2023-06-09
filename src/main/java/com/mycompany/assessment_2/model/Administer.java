/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assessment_2.model;

/**
 * The model of Administer
 *  id：21146528
 * @author Xuanhao Wang
 */
public class Administer extends User {
    public Administer(int id, String userName, String password, String sex, int age,String email) {
        super(id, userName, password, sex, age,email);
        this.setUserType("Administer");
    }
}