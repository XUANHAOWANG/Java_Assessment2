/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assessment_2.view;


import com.mycompany.assessment_2.model.User;
import com.mycompany.assessment_2.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Thd admin frame
 *  id：21146528
 * @author Xuanhao Wang
 */
public class AdminJFrame extends JFrame implements ActionListener {
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton addButton, deleteButton, searchButton, backButton;
    private JTextField searchField;
    private UserService userService;

    /**
     * The constructor of AdminJFrame
     */
    public AdminJFrame() {
        setTitle("Admin");
        setSize(600, 430);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        userService = new UserService();
        initContent();

        setVisible(true);
    }

    /**
     * Init content
     */
    public void initContent() {
        setLayout(null);
        List<User> users = userService.findUsers(null);
        // Create table to display user list
        String[] columnNames = {"ID", "Username", "Password", "Age", "Gender", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(userTable);
        tableScrollPane.setBounds(20, 20, 400, 300);
        add(tableScrollPane);

        // Populate the table with user data
        for (User user : users) {
            Object[] rowData = {user.getId(), user.getUserName(), user.getPassword(), user.getAge(), user.getGender(), user.getEmail()};
            tableModel.addRow(rowData);
        }

        // Create buttons and search field

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(450, 70, 100, 30);
        searchButton = new JButton("Search");
        searchButton.setBounds(450, 220, 100, 30);
        searchField = new JTextField();
        searchField.setBounds(450, 180, 100, 30);

        // Create the Back button
        backButton = new JButton("Back");
        backButton.setBounds(450, 300, 100, 30);

        // Add action listeners

        deleteButton.addActionListener(this);
        searchButton.addActionListener(this);
        backButton.addActionListener(this);

        // Add components to the frame

        add(deleteButton);
        add(searchButton);
        add(searchField);
        add(backButton);
    }

    /**
     * Listener for delete button and serch button and back button
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            // Delete user functionality
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) userTable.getValueAt(selectedRow, 0);
                userService.deleteUser(id);
                tableModel.removeRow(selectedRow);
            }
        } else if (e.getSource() == searchButton) {
            // Search user functionality
            String searchText = searchField.getText();
            List<User> users = userService.findUsers(searchText);
            if (!searchText.isEmpty()) {
                tableModel.setRowCount(0); // Clear the table

                for (User user : users) {
                    if (user.getUserName().contains(searchText)) {
                        Object[] rowData = {user.getId(), user.getUserName(), user.getPassword(), user.getClass().getSimpleName()};
                        tableModel.addRow(rowData);
                    }
                }
            }
        } else if (e.getSource() == backButton) {
            // Close the AdminJFrame
            this.dispose();
            // Open the LoginJFrame
            new LoginJFrame();
        }
    }
}
