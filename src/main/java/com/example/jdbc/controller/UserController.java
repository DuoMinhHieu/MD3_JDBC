package com.example.jdbc.controller;

import com.example.jdbc.model.User;
import com.example.jdbc.service.IUserDAO;
import com.example.jdbc.service.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/users")

public class UserController extends HttpServlet {
    private IUserDAO iUserDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            showAllUser(req, resp);
        }
        else {
            switch (action){
                case "create":
                    showFormCreate(req, resp);
                    break;
            }
        }
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/create.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list =iUserDAO.selectAllUsers();
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/list.jsp");
        req.setAttribute("listUser", list);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            showAllUser(req, resp);
        }
        else {
            int price = Integer.parseInt(req.getParameter("price"));
            int code = Integer.parseInt(req.getParameter("code"));
            String name = req.getParameter("name");
            String branch = req.getParameter("branch");
            String description = req.getParameter("description");
            User u = new User(name, branch, description);
            try {
                iUserDAO.insertUser(u);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            showAllUser(req, resp);
        }
    }
}