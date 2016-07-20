package Servlet;

import JavaBean.InfoBean;
import JavaBean.UserBean;
import Util.SQLConnector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: michael
 * @Date: 16-7-19 上午1:39
 * @Project: S.M.
 * @Package: Servlet
 */
@WebServlet(name = "HandleLogin")
public class HandleLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String forward;
        UserBean userBean = new UserBean();
        InfoBean infoBean = new InfoBean();
        request.getSession().setAttribute("userBean", userBean);
        request.getSession().setAttribute("infoBean", infoBean);

        try {
            SQLConnector connector = new SQLConnector();
            String sql = "select * from user where user_name='"+
                    username+"' and password='"+password+"'";
            ResultSet resultSet = connector.qurey(sql);
            if (resultSet.next()) {
                userBean.setId(resultSet.getInt(userBean.ID));
                userBean.setUsername(resultSet.getString(userBean.USERNAME));
                userBean.setPassword(resultSet.getString(userBean.PASSWORD));
                userBean.setRealname(resultSet.getString(userBean.REALNAME));
                userBean.setEmail(resultSet.getString(userBean.EMAIL));
                userBean.setPhonenum(resultSet.getString(userBean.PHONENUM));
                userBean.setAuthority(resultSet.getBoolean(userBean.AUTHORITY));
                userBean.setIntegral(resultSet.getInt(userBean.INTEGRAL));
                infoBean.setInfo("登录成功！");
                userBean.setState(true);
                forward = "index-after.jsp";
            }
            else {
                infoBean.setInfo("用户名或密码错误，请确认后重新登录。");
                forward = "login.jsp";
                userBean.setState(false);
            }
        } catch (SQLException e) {
            infoBean.setInfo("数据库访问错误,请重试。");
            forward = "login.jsp";
            userBean.setState(false);
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
