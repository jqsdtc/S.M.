package Servlet;

import JavaBean.*;
import Util.SQLConnector;
import com.sun.rowset.CachedRowSetImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        ShowListBean showListBean = null;
        String forward;
        int sign = Integer.parseInt(request.getParameter("sign"));
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
            String sql;
            if (sign == 0) {
                sql = "SELECT * FROM indent WHERE uid='" + sign + "'";
                forward = "";
            }
            else {
                sql = "SELECT * FROM cargo WHERE sort='"+sign+"'";
                forward = "";
            }
            ResultSet resultSet = connector.qurey(sql);
            if (resultSet.next()) {
                rowSet = new CachedRowSetImpl();
                rowSet.populate(resultSet);
                showListBean.setRowSet(rowSet);
                rowSet.last();
                int row = rowSet.getRow();
                int pageAllCount = ((row % showListBean.getPageSize()) == 0) ? (row / showListBean.getPageSize()) : (row / showListBean.getPageSize() + 1);
                showListBean.setPageAllCount(pageAllCount);
                showListBean.setBeanSet(this.genPageUnit(showPage, showListBean.getPageSize(), rowSet, sign != 0, sign));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            forward = "";
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private ArrayList<Object> genPageUnit (int page, int pageSize, CachedRowSetImpl rowSet, boolean isCargo, int sort) {
        ArrayList<Object> pageUnits = new ArrayList<Object>();
        try {
            rowSet.absolute((page - 1) * pageSize + 1);
            for (int i = 1; i < pageSize; i++) {
                if (isCargo) {
                    CargoBean cargoBean = new CargoBean();
                    cargoBean.setCargoname(rowSet.getString(cargoBean.CARGONAME));
                    cargoBean.setId(rowSet.getInt(cargoBean.ID));
                    cargoBean.setInventory(rowSet.getInt(cargoBean.INVENTORY));
                    cargoBean.setPrice(rowSet.getInt(cargoBean.PRICE));
                    cargoBean.setSort(rowSet.getShort(cargoBean.SORT));
                    pageUnits.add(cargoBean);
                }
                else {
                    IndentUnitBean indentUnitBean = new IndentUnitBean();
                    indentUnitBean.setId(rowSet.getInt(indentUnitBean.ID));
                    indentUnitBean.setCid(rowSet.getInt(indentUnitBean.CID));
                    indentUnitBean.setUid(rowSet.getInt(indentUnitBean.UID));
                    indentUnitBean.setDate(rowSet.getString(indentUnitBean.DATE));
                    indentUnitBean.setQuantity(rowSet.getInt(indentUnitBean.QUANTITY));
                    pageUnits.add(indentUnitBean);
                }
            }
            return pageUnits;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
