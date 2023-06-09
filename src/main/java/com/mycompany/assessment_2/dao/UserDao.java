package com.mycompany.assessment_2.dao;

import com.mycompany.assessment_2.model.Administer;
import com.mycompany.assessment_2.model.Gamer;
import com.mycompany.assessment_2.model.User;
import com.mycompany.assessment_2.utils.DBUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * The  class UserDao.
 *  id：21146528
 * @author Xuanhao Wang
 */
public class UserDao {

    /**
     * Initialize the table
     */
    public void initTable() {
        DBUtils.getInstance().executeUpdate("drop table  t_user");

        String createUserSql = "create table t_user (id int primary key GENERATED ALWAYS AS IDENTITY,userName varchar(32)," +
                " password varchar(32), gender varchar(10), age int,email varchar(64),user_type varchar(64))";
        DBUtils.getInstance().executeUpdate(createUserSql);

        User admin = new Administer(1,"ad123","ad123","male",18,"ad123@qq.com");
        addUser(admin);
        User gamer = new Gamer(2,"gamer1","gamer1","male",23,"gamer1@qq.com");
        addUser(gamer);
    }

    /**
     * Add  a user to the table
     * @param user
     */
    public void addUser(User user) {
        String sql = "insert into t_user (userName,password,gender,age,email,user_type) values('" + user.getUserName() +
                "','" + user.getPassword() + "','" + user.getGender() + "'," + user.getAge() + ",'" + user.getEmail() + "','" + user.getUserType() + "')";
        System.out.println(sql);
        DBUtils.getInstance().executeUpdate(sql);
    }

    /**
     * Delete a user from the table
     * @param id
     */
    public void deleteUser(int id) {
        String sql = "delete from t_user where id=" + id;
        System.out.println(sql);
        DBUtils.getInstance().executeUpdate(sql);
    }

    /**
     * Find users by username
     * @param username
     * @return
     */
    public List<User> findUsers(String username) {
        ResultSet rs = null;
        if ("".equals(username) || username == null) {
            String sql = "select * from t_user";
            rs = DBUtils.getInstance().executeQuery(sql);
        } else {
            String sql = "select * from t_user where userName like '%" + username + "%'";
            rs = DBUtils.getInstance().executeQuery(sql);
        }
        List<User> result = new ArrayList<>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserName(rs.getString("userName"));
                    user.setPassword(rs.getString("password"));
                    user.setGender(rs.getString("gender"));
                    user.setAge(rs.getInt("age"));
                    user.setEmail(rs.getString("email"));
                    user.setUserType(rs.getString("user_type"));
                    result.add(user);
                }
            } catch (Exception e) {
                //e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
        return result;
    }
}
