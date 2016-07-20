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
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: michael
 * @Date: 16-7-21 上午3:58
 * @Project: S.M.
 * @Package: ${PACKAGE_NAME}
 */
@WebServlet(name = "HandleAdd")
public class HandleAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
        InfoBean infoBean = (InfoBean) request.getSession().getAttribute("infoBean");
        String forward;
        String address = request.getParameter("address").trim();
        if (infoBean == null) {
            infoBean = new InfoBean();
            request.getSession().setAttribute("infoBean", infoBean);
        }
        if (userBean == null || !userBean.isState()) {
            forward = "";
            infoBean.setInfo("您还未登陆，请登录后重试。");
        }
        try {
            SQLConnector connector = new SQLConnector();
            String sql = "INSERT INTO address(uid,address) VALUES('"+userBean.getId()+"','"+address+"')";
            connector.update(sql);
            forward = "";

        } catch (SQLException e) {
            forward = "errorPage.jsp";
            infoBean.setInfo("数据库访问错误，请重试。");
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
