package JavaBean;

import java.util.ArrayList;

/**
 * @Author: michael
 * @Date: 16-7-21 上午1:38
 * @Project: S.M.
 * @Package: JavaBean
 */
public class IndentBean {
    ArrayList<CargoBean> indentUnitBeanList = null;
    float priceAllCount;

    public float getPriceAllCount() {
        return priceAllCount;
    }

    public void setPriceAllCount(float priceAllCount) {
        this.priceAllCount = priceAllCount;
    }

    public ArrayList<CargoBean> getIndentUnitBeanList() {
        return indentUnitBeanList;
    }

    public void setIndentUnitBeanList(ArrayList<CargoBean> indentUnitBeanList) {
        this.indentUnitBeanList = indentUnitBeanList;
    }
}
