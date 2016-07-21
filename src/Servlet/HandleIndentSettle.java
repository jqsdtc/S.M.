package Servlet;

import JavaBean.CargoBean;
import JavaBean.IndentBean;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

/**
 * @Author: michael
 * @Date: 16-7-21 上午2:20
 * @Project: S.M.
 * @Package: Servlet
 */
@WebServlet(name = "HandleIndentSettle")
public class HandleIndentSettle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        IndentBean indentBean = (IndentBean) request.getSession().getAttribute("indentBean");
        InfoBean infoBean = (InfoBean) request.getSession().getAttribute("InfoBean");
        UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
        String forward;
        int integral;
        if (infoBean == null) {
            infoBean = new InfoBean();
            request.getSession().setAttribute("infoBean", infoBean);
        }
        if (userBean == null || !userBean.isState()) {
            forward = "";
            infoBean.setInfo("您还未登陆，请登录后重试。");
        }
        if (indentBean == null) {
            forward = "errorPage.jsp";
            infoBean.setInfo("你的购物车空空如也，快去选购一些商品吧。");
        }
        else {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(date);
            try {
                for (CargoBean cargoBean: indentBean.getIndentUnitBeanList()) {
                    SQLConnector connector = new SQLConnector();
                    String sql = "INSERT INTO indent(cid,uid,date,quantity) VALUES('"+cargoBean.getId()+"','"+userBean.getId()+"','"+currentTime+"','"+cargoBean.getQuantity()+"')";
                    connector.update(sql);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            forward = "";
            if (indentBean.getPriceAllCount() < userBean.getIntegral()/10)
                indentBean.setPriceAllCount(0);
            else
                indentBean.setPriceAllCount(indentBean.getPriceAllCount() - (userBean.getIntegral())/10);
            userBean.setIntegral(userBean.getIntegral() + (int)indentBean.getPriceAllCount());
            indentBean.setIndentUnitBeanList(new ArrayList<CargoBean>());
            indentBean.setEmpty(true);
            indentBean.setPriceAllCount(0);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
