package Servlet;

import JavaBean.InfoBean;
import JavaBean.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: michael
 * @Date: 16-7-21 上午1:15
 * @Project: S.M.
 * @Package: Servlet
 */
@WebServlet(name = "UserDetial")
public class UserDetial extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        UserBean userBean = (UserBean)request.getSession().getAttribute("userBean");
        InfoBean infoBean = (InfoBean)request.getSession().getAttribute("infoBean");
        if (infoBean == null) {
            infoBean = new InfoBean();
            request.getSession().setAttribute("infoBean", infoBean);
        }
        String forward = null;
        if (userBean == null || !userBean.isState()) {
            forward = "";
            infoBean.setInfo("您还未登陆，请登录后重试。");
        }
        else {
            forward = "";
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
