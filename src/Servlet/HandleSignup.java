package Servlet;

import JavaBean.UserBean;
import Util.SQLConnector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: michael
 * @Date: 16-7-19 上午1:39
 * @Project: S.M.
 * @Package: Servlet
 */
@WebServlet(name = "HandleSignup")
public class HandleSignup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String realname = request.getParameter("realname").trim();
        String email = request.getParameter("email").trim();
        String phonenum = request.getParameter("phonenum").trim();
        UserBean userBean = new UserBean();
        request.getSession().setAttribute("userBean",userBean);
        String forward;

        try {
            SQLConnector connector = new SQLConnector();
            String sql = "INSERT INTO user(user_name,password,realname,user_Email,real_name) VALUES('"+username+"','"+password+"','"+realname+"','"+email+"','"+phonenum+"')";
            connector.update(sql);
            forward = "index.jsp";

            userBean.setUsername(username);
            userBean.setPassword(password);
            userBean.setRealname(realname);
            userBean.setEmail(email);
            userBean.setPhonenum(phonenum);
        } catch (SQLException e) {
            userBean.setInfo("此用户名已被使用，请更改。");
            forward = "register.jsp";
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
