package Servlet;

import JavaBean.CargoBean;
import JavaBean.IndentBean;
import JavaBean.InfoBean;
import JavaBean.ShowListBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: michael
 * @Date: 16-7-19 下午7:03
 * @Project: S.M.
 * @Package: Servlet
 */
@WebServlet(name = "HandleDetial")
public class HandleDetial extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        ShowListBean showListBean = (ShowListBean) request.getSession().getAttribute("showListBean");
        int id = Integer.parseInt(request.getParameter("ID"));
        String type = request.getParameter("type");
        String forward = null;
        CargoBean cargoBean = null;
        IndentBean indentBean = null;
        InfoBean infoBean = (InfoBean) request.getSession().getAttribute("infoBean");
        if (infoBean == null) {
            infoBean = new InfoBean();
            request.getSession().setAttribute("infoBean", infoBean);
        }
        for (Object bean: showListBean.getBeanSet()) {
            if (type.equals("cargo") && ((CargoBean)bean).getId() == id) {
                cargoBean = (CargoBean)bean;
                request.getSession().setAttribute("cargoBean", cargoBean);
                forward = "";
                break;
            }
            else if (type.equals("indent") && ((IndentBean)bean).getId() == id) {
                indentBean = (IndentBean)bean;
                request.getSession().setAttribute("indentBean", indentBean);
                forward = "";
                break;
            }
        }
        if (cargoBean == null && indentBean == null || forward == null) {
            forward = "errorPage.jsp";
            if (type.equals("cargo"))
                infoBean.setInfo("商品不存在。");
            else
                infoBean.setInfo("订单不存在。");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
