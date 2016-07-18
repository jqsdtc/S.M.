package JavaBean;

import com.sun.rowset.CachedRowSetImpl;

/**
 * @Author: michael
 * @Date: 16-7-19 上午3:43
 * @Project: S.M.
 * @Package: JavaBean
 */
public class ShowListBean {
    private CachedRowSetImpl rowSet = null;
    private int pageSize = 8;
    private int pageAllCount = 0;
    private int showPage = 1;
    private StringBuffer presentPageResult;

    public CachedRowSetImpl getRowSet() {
        return rowSet;
    }

    public void setRowSet(CachedRowSetImpl rowSet) {
        this.rowSet = rowSet;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageAllCount() {
        return pageAllCount;
    }

    public void setPageAllCount(int pageAllCount) {
        this.pageAllCount = pageAllCount;
    }

    public int getShowPage() {
        return showPage;
    }

    public void setShowPage(int showPage) {
        this.showPage = showPage;
    }

    public StringBuffer getPresentPageResult() {
        return presentPageResult;
    }

    public void setPresentPageResult(StringBuffer presentPageResult) {
        this.presentPageResult = presentPageResult;
    }
}
