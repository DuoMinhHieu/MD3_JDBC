package com.example.jdbc.service;

import com.example.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{
    public static final String URL = "jdbc:mysql://localhost:3306/minitest";
    public static final String USER = "root";
    public static final String PASSWORD = "Minhhieu@123";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minitest", "root", "Minhhieu@123");
        } catch (ClassNotFoundException e) {
            System.out.println("ket noi khong thanh cong");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("ket noi khono thanh cong");
            throw new RuntimeException(e);
        }
        System.out.println("ket noi thanh cong");
        return connection;
    }

    public static void main(String[] args) {
        UserDAO c = new UserDAO();
        Connection c1 = c.getConnection();
        List<User> userList = c.selectAllUsers();
        System.out.println(userList);
    }


    @Override
    public void insertUser(User user) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("insert into users(name, branch, description) values(?,?,?);");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.branch());
        preparedStatement.setString(3, user.description());
        preparedStatement.executeUpdate();
    }

    @Override
    public User selectUser(int code) {
        return null;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("select * from users;");
            ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    int price = resultSet.getInt("price");
                    int code = resultSet.getInt("code");
                    String name = resultSet.getString("name");
                    String branch = resultSet.getString("branch");
                    String description = resultSet.getString("description");
                    User user = new User(price, code, name, branch, description);
                    userList.add(user);
                }
                return userList;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return false;
    }
}
