package JavaBean;

/**
 * @Author: michael
 * @Date: 16-7-19 上午12:49
 * @Project: S.M.
 * @Package: JavaBean
 */
public class IndentBean {
    private int id;
    private int uid;
    private int cid;
    private String date;
    private int quantity;

    public final short ID = 1;
    public final short UID = 2;
    public final short CID = 3;
    public final short DATE = 4;
    public final short QUANTITY = 5;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int mid) {
        this.cid = cid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
