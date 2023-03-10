package com.example.elearning.Controller;

import com.example.elearning.Service.AccountService;
import com.example.elearning.Service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AuthenticationServlet", value = "/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {

    AccountService accountService = new AccountService();
    StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("signin")) {
            try {
                signInHandler(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void signInHandler(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (accountService.checkUserCredentials(username, password) != null) {
            request.getSession().setAttribute("account", accountService.getAccount(username));
            request.getSession().setAttribute("user", studentService.getStudent(accountService.getAccount(username).getStudent_id()));
            request.getRequestDispatcher("home.jsp").forward(request, response);
            RequestDispatcher disp = request.getRequestDispatcher("home.jsp");
            disp.forward(request, response);
        }
    }

}
