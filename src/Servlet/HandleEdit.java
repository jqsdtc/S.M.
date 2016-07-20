package Servlet;

import JavaBean.AddressBean;
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
 * @Date: 16-7-21 上午3:28
 * @Project: S.M.
 * @Package: Servlet
 */
@WebServlet(name = "HandleEdit")
public class HandleEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String editType = request.getParameter("editType").trim();
        UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
        InfoBean infoBean = (InfoBean) request.getSession().getAttribute("infoBean");
        String forward;
        if (infoBean == null) {
            infoBean = new InfoBean();
            request.getSession().setAttribute("infoBean", infoBean);
        }
        if (userBean == null || !userBean.isState()) {
            forward = "";
            infoBean.setInfo("您还未登陆，请登录后重试。");
        }
        else {
            String sql;
            if (editType.equals("editAddress")) {
                AddressBean addressBean = (AddressBean) request.getSession().getAttribute("addressBean");
                if (addressBean == null) {
                    forward = "errorPage.jsp";
                    infoBean.setInfo("地址无效，请刷新后重试。");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
                    requestDispatcher.forward(request, response);
                    return;
                }
                sql = "UPDATE address SET address='" + request.getParameter("address").trim() + "' WHERE id='" + addressBean.getId() + "'";
            }
            else if (editType.equals("changPassword"))
                sql = "UPDATE user SET password='" + request.getParameter("password").trim() + "' WHERE id='" + userBean.getId() + "'";
            else sql = "UPDATE user SET email='" + request.getParameter("email").trim() +
                    "',realname='" + request.getParameter("realname").trim()+
                    "',phonenum='" + request.getParameter("phonenum").trim()+
                    "' WHERE id='" + userBean.getId() + "'";

            try {
                SQLConnector connector = new SQLConnector();
                connector.update(sql);
                forward = "index.jsp";
                if (editType.equals("changPassword"))
                    userBean.setPassword(request.getParameter("password").trim());
                else {
                    userBean.setEmail(request.getParameter("email").trim());
                    userBean.setRealname(request.getParameter("realname").trim());
                    userBean.setPhonenum(request.getParameter("phonenum").trim());
                }
            } catch (SQLException e) {
                forward = "errorPage.jsp";
                infoBean.setInfo("数据库访问错误，请重试。");
                e.printStackTrace();
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
        requestDispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
