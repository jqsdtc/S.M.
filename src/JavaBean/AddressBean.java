package JavaBean;

/**
 * @Author: michael
 * @Date: 16-7-19 上午12:45
 * @Project: S.M.
 * @Package: JavaBean
 */
public class AddressBean {
    private int id;
    private int Uid;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
