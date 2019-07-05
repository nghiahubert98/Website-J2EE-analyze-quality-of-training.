package uit.model;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String password;
    private String code;
    private int userType;
    private String salt;
    private List<String> roles;
    private boolean status;

    public User() {}
    
    public User(int id, String name, String password, String code, int userType, String salt, List<String> roles,
            boolean status) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.code = code;
        this.userType = userType;
        this.salt = salt;
        this.roles = roles;
        this.status = status;
    }

    public User(int id, String name, String password, int userType, String salt, String code, List<String> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userType = userType;
        this.salt = salt;
        this.code = code;
        this.roles = roles;
    }

    public User(int id, String name, String code, int userType, List<String> roles, boolean status) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.userType = userType;
        this.roles = roles;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public int getUserType() {
        return userType;
    }
    public void setUserType(int userType) {
        this.userType = userType;
    }
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
