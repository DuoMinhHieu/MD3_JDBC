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
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("ket noi khong thanh cong");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("ket noi khong thanh cong");
            throw new RuntimeException(e);
        }
        System.out.println("ket noi thanh cong");
        return connection;
    }


    public static void main(String[] args) {
        UserDAO c = new UserDAO();
        c.selectAllUsers();
    }

    List<User> userList = new ArrayList<>();
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
        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users;");
            ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    int price = resultSet.getInt("price");
                    int code = resultSet.getInt("code");
                    String name = resultSet.getString("name");
                    String branch = resultSet.getString("branch");
                    String description = resultSet.getString("description");
                    User user = new User(price,code,name,branch,description);
                    userList.add(user);
                    System.out.println(user);
                }
                return userList;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public boolean deleteUser(int code) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setInt(1, code);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new SQLException("Error deleting user with code " + code, e);
        }
    }
    @Override
    public boolean updateUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ?, branch = ?, description = ? WHERE id = ?")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getBranch());
            preparedStatement.setString(3, user.getDescription());
            preparedStatement.setInt(4, user.getCode());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new SQLException("Error updating user with id " + user.getCode(), e);
        }
    }
}

