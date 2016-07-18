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
}
