package JavaBean;

/**
 * @Author: michael
 * @Date: 16-7-19 上午12:41
 * @Project: S.M.
 * @Package: JavaBean
 */
public class UserBean {
    private int id;
    private String username;
    private String password;
    private boolean authority;
    private String realname;
    private String phonenum;
    private String email;
    private int integral;
    private boolean state;

    public final short ID = 1;
    public final short USERNAME = 2;
    public final short PASSWORD = 3;
    public final short AUTHORITY = 4;
    public final short REALNAME = 5;
    public final short PHONENUM = 6;
    public final short EMAIL = 7;
    public final short INTEGRAL = 8;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
