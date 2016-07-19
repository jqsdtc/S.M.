package Servlet;

import JavaBean.ShowListBean;
import JavaBean.UserBean;
import Util.SQLConnector;
import com.sun.rowset.CachedRowSetImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: michael
 * @Date: 16-7-19 上午3:42
 * @Project: S.M.
 * @Package: ${PACKAGE_NAME}
 */
@WebServlet(name = "HandleShow")
public class HandleShow extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        StringBuffer presentPageResult = new StringBuffer();
        ShowListBean showListBean = null;
        String forward;
        int sort = Integer.parseInt(request.getParameter("sort"));
        int showPage;
        CachedRowSetImpl rowSet = null;
        UserBean userBean = (UserBean)request.getSession().getAttribute("userBean");
        if (userBean == null) {

        }
        else {

        }
        request.getSession().setAttribute("showListBean", showListBean);
        String pageSizeGet = request.getParameter("pageSize");
        if (pageSizeGet != null) {
            try {
                int size = Integer.parseInt(pageSizeGet);
                showListBean.setPageSize(size);
            } catch(NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if(request.getParameter("showPage") == null){
            showPage = 1;
        }
        else {
            showPage = Integer.parseInt(request.getParameter("showPage"));
            System.out.println(showPage);
            if(showPage > showListBean.getPageAllCount()) {
                showPage = showListBean.getPageAllCount();
            }
            if(showPage <= 0) {
                showPage = 1;
            }
        }
        showListBean.setShowPage(showPage);

        try {
            SQLConnector connector = new SQLConnector();
            String sql = "SELECT * FROM cargo WHERE sort='"+sort+"'";
            ResultSet resultSet = connector.qurey(sql);
            if (resultSet.next()) {
                rowSet = new CachedRowSetImpl();
                rowSet.populate(resultSet);
                showListBean.setRowSet(rowSet);
                rowSet.last();
                int row = rowSet.getRow();
                int pageAllCount = ((row % showListBean.getPageSize()) == 0) ? (row / showListBean.getPageSize()) : (row / showListBean.getPageSize() + 1);
                showListBean.setPageAllCount(pageAllCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
