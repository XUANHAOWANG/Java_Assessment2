/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assessment_2.view;

/**
 *  id：21146528
 * @author Xuanhao Wang
 */

import com.mycompany.assessment_2.model.User;
import com.mycompany.assessment_2.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The login frame.
 */
public class LoginJFrame extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    private UserService userService;

    public LoginJFrame() {
       
        //login frame
        //sett Login window
        this.setSize(488, 430);
        this.setTitle("Login");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initContent();

        setVisible(true);
        userService = new UserService();
    }

    public void initContent() {
        this.setLayout(null);

        //Add all components to the login window and set the parameters of the components
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(70, 80, 80, 80);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(70, 130, 80, 80);
        usernameField = new JTextField(15);
        usernameField.setBounds(155, 100, 180, 40);
        passwordField = new JPasswordField(15);
        passwordField.setBounds(155, 150, 180, 40);

        //create two button
        loginButton = new JButton("Login");
        loginButton.setBounds(130, 250, 100, 50);
        registerButton = new JButton("Register");
        registerButton.setBounds(270, 250, 100, 50);

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        this.getContentPane().add(usernameLabel);
        this.getContentPane().add(usernameField);
        this.getContentPane().add(passwordLabel);
        this.getContentPane().add(passwordField);
        this.getContentPane().add(registerButton);
        this.getContentPane().add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if(null==username||"".equals(username)){
                JOptionPane.showMessageDialog(this, "Username cannot be empty!");
                return;
            }
            if(null==password||"".equals(password)){
                JOptionPane.showMessageDialog(this, "Password cannot be empty!");
                return;
            }
            User authenticatedUser = null;
            List<User> users = userService.findUsers(null);
            for (User user : users) {
                if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                    authenticatedUser = user;
                    break;
                }
            }

            if (authenticatedUser != null) {
                System.out.println("Authenticated!");
                // Close the login window
                this.dispose();

                if (authenticatedUser.getUserType().equals("Administer")) {
                    // Open the administer window
                    new AdminJFrame();
                } else if (authenticatedUser.getUserType().equals("Gamer")) {
                    // Open the game window
                    new GameJFrame();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
                System.out.println("Invalid credentials!");
                return;
                // Show an error message or clear the input fields
            }
        } else if (e.getSource() == registerButton) {
            //close login window
            this.dispose();
            // open register window
            new RegisterJFrame();
        }
    }
}
