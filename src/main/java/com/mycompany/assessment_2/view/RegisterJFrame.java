/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assessment_2.view;

/**
 *  id：21146528
 * @author Xuanhao Wang
 */

import com.mycompany.assessment_2.model.Administer;
import com.mycompany.assessment_2.model.Gamer;
import com.mycompany.assessment_2.model.User;
import com.mycompany.assessment_2.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterJFrame extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField ageField;
    private JTextField emailField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton backButton;
    private JComboBox<String> userTypeComboBox;
    private JComboBox<String> genderComboBox;
    private UserService userService;

    public RegisterJFrame() {
        setTitle("Register");
        setSize(488, 620);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initContent();

        setVisible(true);
        userService = new UserService();
    }

    public void initContent() {
        //Generate Register page and components
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(70, 80, 80, 80);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(70, 130, 80, 80);
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(20, 180, 120, 80);

        usernameField = new JTextField(15);
        usernameField.setBounds(155, 100, 180, 40);
        passwordField = new JPasswordField(15);
        passwordField.setBounds(155, 150, 180, 40);
        confirmPasswordField = new JPasswordField(15);
        confirmPasswordField.setBounds(155, 200, 180, 40);

        JLabel ageLabel = new JLabel("age:");
        ageLabel.setBounds(99, 306, 53, 80);
        ageField = new JTextField(15);
        ageField.setBounds(155, 327, 180, 40);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(70, 376, 80, 80);
        String[] genders = {"male", "female"};
   genderComboBox = new JComboBox<String>(genders);
        genderComboBox.setBounds(155, 396, 180, 40);

        JLabel emailLabel = new JLabel("email:");
        emailLabel.setBounds(99, 241, 53, 80);
        emailField = new JTextField(15);
        emailField.setBounds(155, 262, 180, 40);

        JLabel userTypeLabel = new JLabel("User Type:");
        userTypeLabel.setBounds(70, 450, 80, 50);
        String[] userTypes = {"Administer", "Gamer"};
        userTypeComboBox = new JComboBox<>(userTypes);
        userTypeComboBox.setBounds(155, 450, 180, 40);



        registerButton = new JButton("Register");
        registerButton.setBounds(247, 533, 100, 50);
        backButton = new JButton("Back");
        backButton.setBounds(99, 533, 100, 50);

        getContentPane().setLayout(null);
        getContentPane().add(usernameLabel);
        getContentPane().add(usernameField);
        getContentPane().add(passwordLabel);
        getContentPane().add(passwordField);
        getContentPane().add(confirmPasswordLabel);
        getContentPane().add(confirmPasswordField);
        getContentPane().add(userTypeLabel);
        getContentPane().add(userTypeComboBox);
        getContentPane().add(registerButton);
        getContentPane().add(backButton);
        getContentPane().add(ageField);
        getContentPane().add(ageLabel);
        getContentPane().add(genderLabel);
        getContentPane().add(genderComboBox);
        getContentPane().add(emailLabel);
        getContentPane().add(emailField);

        registerButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String ageStr = ageField.getText();
            String email = emailField.getText();
            //Evaluate the user's input text
            if(!userService.isValidUsername(username)){
                JOptionPane.showMessageDialog(this, "Username must between 4 and 13 characters.");
                return;
            }
            if(!userService.isValidPassword(password)){
                JOptionPane.showMessageDialog(this, "Password must between 4 and 13 characters.");
                return;
            }
            if(!password.equals(confirmPassword)){
                JOptionPane.showMessageDialog(this, "Different passwords!");
                return;
            }
            if(!userService.isValidAge(ageStr)){
                JOptionPane.showMessageDialog(this, "Age must be between 0 and 100.");
                return;
            }
            if(!userService.isValidEmail(email)){
                JOptionPane.showMessageDialog(this, "Email must be valid.");
                return;
            }

            String userType = (String) userTypeComboBox.getSelectedItem();
            String gender = (String) genderComboBox.getSelectedItem();
            if (userType.equals("Administer")) {
                User user = new Administer(-1, username, password,gender,Integer.parseInt(ageStr),email);
                userService.addUser(user);
            } else {
                User user = new Gamer(-1, username, password,gender,Integer.parseInt(ageStr),email);
                userService.addUser(user);
            }

            System.out.println("User registered: " + username);
            JOptionPane.showMessageDialog(this, "Registration successful!");
            dispose();
            new LoginJFrame();
        } else if (e.getSource() == backButton) {
            dispose();
            new LoginJFrame();
        }
    }
}
