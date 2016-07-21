package JavaBean;

/**
 * @Author: michael
 * @Date: 16-7-19 上午12:47
 * @Project: S.M.
 * @Package: JavaBean
 */
public class CargoBean {
    private int id;
    private String cargoname;
    private int inventory;
    private float price;
    private short sort;
    private int quantity;

    public final short ID = 1;
    public final short CARGONAME = 2;
    public final short INVENTORY = 3;
    public final short PRICE = 4;
    public final short SORT = 5;

    public final short SHOE = 6;
    public final short CLOTHES = 7;
    public final short ORNAMENT = 8;
    public final short TOY = 9;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargoname() {
        return cargoname;
    }

    public void setCargoname(String cargoname) {
        this.cargoname = cargoname;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public short getSort() {
        return sort;
    }

    public void setSort(short sort) {
        this.sort = sort;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
